package com.hlh.boot.service.impl;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.hlh.boot.mapper.UserMapper;
import com.hlh.boot.model.dto.BindPhoneDto;
import com.hlh.boot.model.dto.LoginDto;
import com.hlh.boot.model.dto.WxLoginDto;
import com.hlh.boot.model.entity.User;
import com.hlh.boot.service.RedisService;
import com.hlh.boot.service.UserService;
import com.hlh.boot.util.AliyunResource;
import com.hlh.boot.util.FileResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisService redisService;
    @Resource
    private AliyunResource aliyunResource;
    @Resource
    private FileResource fileResource;

    @Override
    public boolean login(LoginDto loginDto){
        User user = getUser((loginDto.getPhone()));
        if (user != null){
            return loginDto.getPassword().equals(user.getPassword());
        }
        return false;
    }
    @Override
    public User getUser(String phone){
        return userMapper.findUserByPhone(phone);
    }

    @Override
    public User updateUser(User user){
        User savedUser = getUser(user.getPhone());
        if (!user.getPassword().equals(savedUser.getPassword())){
            savedUser.setPassword(DigestUtils.md5Hex(user.getPassword()));
        }else{
            savedUser.setPassword(user.getPassword());
        }
        savedUser.setNickname(user.getNickname());
        savedUser.setAvatar(user.getAvatar());
        savedUser.setGender(user.getGender());
        savedUser.setBirthday(user.getBirthday());
        savedUser.setAddress(user.getAddress());
        userMapper.updateUser(savedUser);
        return savedUser;
    }

    @Override
    public boolean loginByCode(LoginDto loginDto) {
        boolean flag = redisService.existsKey(loginDto.getPhone());
        System.out.println(flag);
        if (flag){
            String saveCode = redisService.getValue(loginDto.getPhone(), String.class);
            if (saveCode.equalsIgnoreCase(loginDto.getCode())){
                User user = getUser(loginDto.getPhone());
                if (user == null){
                    User saveUser = User.builder().phone(loginDto.getPhone()).nickname("?????????").avatar("/static/img/nologin.jpeg")
                            .createTime(new Date()).build();
                    userMapper.insert(saveUser);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public User findByOpenId(String wxOpenId){
        return userMapper.fineUserByOpenId(wxOpenId);
    }

    @Override
    public User wxLogin(WxLoginDto wxLoginDto){
        User user = findByOpenId(wxLoginDto.getWxOpenId());

        if (user == null){
            user = User.builder()
                    .phone("")
                    .wxOpenId(wxLoginDto.getWxOpenId())
                    .nickname(wxLoginDto.getNickname())
                    .avatar(wxLoginDto.getAvatar())
                    .gender(wxLoginDto.getGender())
                    .address("??????????????????")
                    .createTime(new Date()).build();
            userMapper.insert(user);
        }
        return user;
    }

    @Override
    public String uploadFile(MultipartFile file){
        String endPoint = fileResource.getEndPoint();
        String accessKeyId = aliyunResource.getAccessKeyId();
        String accessKeySecret = aliyunResource.getAccessKeySecret();

        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
        String fileName = file.getOriginalFilename();

        assert fileName != null;
        String[] fileNameArr = fileName.split("\\.");
        String suffix = fileNameArr[fileNameArr.length - 1];

        String uploadFileName = fileResource.getObjectName() + UUID.randomUUID().toString() + "." + suffix;
        InputStream inputStream = null;
        try{
            inputStream = file.getInputStream();
        }catch (IOException e){
            System.err.println("????????????????????????");
        }
        ossClient.putObject(fileResource.getBucketName(), uploadFileName, inputStream);
        ossClient.shutdown();
        return uploadFileName;
    }

    @Override
    public User bindPhone(BindPhoneDto bindPhoneDto) {
        // ???????????????????????????????????????????????????
        User savedUser = userMapper.findUserByPhone(bindPhoneDto.getPhone());
        log.info("1-----" + savedUser);
        // ??????redis???????????????????????????????????????
        boolean flag = redisService.existsKey(bindPhoneDto.getPhone());
        log.info("2------" + flag);
        if (flag) {
            // ???????????????
            String saveCode = redisService.getValue(bindPhoneDto.getPhone(), String.class);
            //???????????????
            if (saveCode.equalsIgnoreCase(bindPhoneDto.getCode())) {
                // ??????????????????wxOpenId??????????????????????????????
                if (savedUser.getWxOpenId() == null || savedUser.getWxOpenId().trim().length() == 0) {
                    // ??????wxOpenId???????????????????????????????????????,????????????????????????????????????????????????????????????
                    userMapper.deleteUserByOpenId(bindPhoneDto.getWxOpenId());
                    //??????????????????wxOpenId
                    savedUser.setWxOpenId(bindPhoneDto.getWxOpenId());
                    log.info("3-----" + savedUser);
                    // ???????????????????????????????????????????????????wxOpenId???
                    userMapper.bandPhone(bindPhoneDto.getPhone(), bindPhoneDto.getWxOpenId());
                    savedUser = userMapper.findUserByPhone(bindPhoneDto.getPhone());
                }
            }
        }
        // ??????????????????
        return savedUser;
    }
}
