package com.hlh.boot.controller;


import com.hlh.boot.common.ResponseResult;
import com.hlh.boot.common.ResultCode;
import com.hlh.boot.model.dto.BindPhoneDto;
import com.hlh.boot.model.dto.LoginDto;
import com.hlh.boot.model.dto.WxLoginDto;
import com.hlh.boot.model.entity.User;
import com.hlh.boot.service.RedisService;
import com.hlh.boot.service.UserService;
import com.hlh.boot.util.FileResource;
import com.hlh.boot.util.SmsUtil;
import com.hlh.boot.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/api/v1/users")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private SmsUtil smsUtil;
    @Resource
    private RedisService redisService;

    @Resource
    private FileResource fileResource;

    @PostMapping(value = "/login")
    public ResponseResult login(@RequestBody LoginDto loginDto){
        log.info("loginDto:" + loginDto);
        boolean flag = userService.login(loginDto);
        if (flag){
            return ResponseResult.success(userService.getUser(loginDto.getPhone()));
        }else {
            return ResponseResult.failure(ResultCode.USER_SIGN_IN_FAIL);
        }
    }

    @PostMapping(value = "/sms")
    public ResponseResult sendSms(@RequestParam String phone){
        String code = StringUtil.getVerifyCode();

        boolean flag = smsUtil.sendSms(phone, code);

//        redisService.set(phone, code, 1L);
        if (flag){
            redisService.set(phone, code, 1L);
            return ResponseResult.success(code);
        }else {
            redisService.set(phone, code, 1L);
            return ResponseResult.failure(ResultCode.SMS_ERROR);
        }
    }
    @PostMapping(value = "/login/sms")
    public ResponseResult loginByCode(@RequestBody LoginDto loginDto) {
        log.info("loginDto:" + loginDto);
        boolean flag = userService.loginByCode(loginDto);
        if (flag) {
            return ResponseResult.success(userService.getUser(loginDto.getPhone()));
        }else {
            return ResponseResult.failure(ResultCode.USER_SIGN_IN_FAIL);
        }
    }
    @PostMapping(value = "/update")
    public User update(@RequestBody User user){
        log.info("user:" + user);
        return userService.updateUser(user);
    }

    @PostMapping(value = "/upload")
    public ResponseResult uploadFile(MultipartFile file){
        log.info("开始上传");

        String path = null;
        if (file != null){
            String fileName = file.getOriginalFilename();
            log.info(fileName);
            path = userService.uploadFile(file);
        }
        if(StringUtils.isNotBlank(path)){
            path = fileResource.getOssHost() + path;
            log.info(path);
        }
        return ResponseResult.success(path);
    }

    @PostMapping(value = "/login/wx")
    public ResponseResult wxLogin(@RequestBody WxLoginDto wxLoginDto){
        log.info("wxLoginDto:" + wxLoginDto);
        User user = userService.wxLogin(wxLoginDto);
        if (user == null){
            log.info("新用户");
            return ResponseResult.success(userService.findByOpenId(wxLoginDto.getWxOpenId()));
        }
        return ResponseResult.success(userService.findByOpenId(wxLoginDto.getWxOpenId()));
    }

    @PostMapping(value = "/bind")
    public ResponseResult bindPhone(@RequestBody BindPhoneDto bindPhoneDto) {
        log.info(String.valueOf(bindPhoneDto));
        User user = userService.bindPhone(bindPhoneDto);
        return ResponseResult.success(user);
    }

}
