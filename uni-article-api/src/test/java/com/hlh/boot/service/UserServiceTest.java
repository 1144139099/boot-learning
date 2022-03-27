package com.hlh.boot.service;

import com.hlh.boot.common.Gender;
import com.hlh.boot.model.dto.LoginDto;
import com.hlh.boot.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Slf4j
class UserServiceTest {

    @Resource
    private UserService userService;

//    @Test
//    void login() {
//        LoginDto loginDto = LoginDto.builder()
//                .phone("13851826167")
//                .password("123456")
//                .build();
//        boolean flag = userService.login(loginDto);
//        assertTrue(flag);
//    }

    @Test
    void getUser() {
        User user = userService.getUser("13851826167");
        assertNotNull(user);
        log.info(String.valueOf(user));
    }

    @Test
    void updateUser() {
        User user = User.builder()
                .phone("13851826167")
                .password(DigestUtils.md5Hex("123456"))
                .nickname("hlh")
                .avatar("https://riddler.oss-cn-shanghai.aliyuncs.com/img/hlh.jpg")
                .gender(Gender.SECRET.getKey())
                .birthday(LocalDate.of(1990, 10, 10))
                .address("美国洛杉矶")
                .build();
        userService.updateUser(user);
    }
}