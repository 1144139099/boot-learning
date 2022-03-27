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
                    User saveUser = User.builder().phone(loginDto.getPhone()).nickname("新用户").avatar("/static/img/nologin.jpeg")
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
                    .address("江苏省南京市")
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
            System.err.println("上传文件出现异常");
        }
        ossClient.putObject(fileResource.getBucketName(), uploadFileName, inputStream);
        ossClient.shutdown();
        return uploadFileName;
    }

    @Override
    public User bindPhone(BindPhoneDto bindPhoneDto) {
        // 此时根据手机号查出数据库中用户信息
        User savedUser = userMapper.findUserByPhone(bindPhoneDto.getPhone());
        log.info("1-----" + savedUser);
        // 检查redis中是否有该手机号存储的短信
        boolean flag = redisService.existsKey(bindPhoneDto.getPhone());
        log.info("2------" + flag);
        if (flag) {
            // 取出验证码
            String saveCode = redisService.getValue(bindPhoneDto.getPhone(), String.class);
            //验证码通过
            if (saveCode.equalsIgnoreCase(bindPhoneDto.getCode())) {
                // 该用户对应的wxOpenId如果空，表示还没绑定
                if (savedUser.getWxOpenId() == null || savedUser.getWxOpenId().trim().length() == 0) {
                    // 删除wxOpenId对应的用户记录（合并账号）,要先做这条语句哦，要不然会把主账号也删掉
                    userMapper.deleteUserByOpenId(bindPhoneDto.getWxOpenId());
                    //补全该用户的wxOpenId
                    savedUser.setWxOpenId(bindPhoneDto.getWxOpenId());
                    log.info("3-----" + savedUser);
                    // 更新该手机号对应的记录信息（持久化wxOpenId）
                    userMapper.bandPhone(bindPhoneDto.getPhone(), bindPhoneDto.getWxOpenId());
                    savedUser = userMapper.findUserByPhone(bindPhoneDto.getPhone());
                }
            }
        }
        // 返回用户信息
        return savedUser;
    }
}
