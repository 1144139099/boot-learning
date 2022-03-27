package com.hlh.boot.service;

import com.hlh.boot.model.dto.BindPhoneDto;
import com.hlh.boot.model.dto.LoginDto;
import com.hlh.boot.model.dto.WxLoginDto;
import com.hlh.boot.model.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    boolean login(LoginDto loginDto);

    User getUser(String phone);

    boolean loginByCode(LoginDto loginDto);

    User updateUser(User user);

    String uploadFile(MultipartFile file);

    User wxLogin(WxLoginDto wxLoginDto);

    User findByOpenId(String wxOpenId);

    User bindPhone(BindPhoneDto bindPhoneDto);
}
