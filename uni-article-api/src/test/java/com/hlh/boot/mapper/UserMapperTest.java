package com.hlh.boot.mapper;

import com.hlh.boot.common.Gender;
import com.hlh.boot.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Slf4j
class UserMapperTest {

    @Resource
    private UserMapper userMapper;

//    @Test
//    void insert() throws Exception{
//        User user = User.builder()
//                .phone("13851826167")
//                .password("123456")
//                .nickname("江户川呱呱")
//                .avatar("https://niit-soft.oss-cn-hangzhou.aliyuncs.com/avatar/me.jpg")
//                .gender(1)
//                .birthday(LocalDate.now())
//                .address("江苏南京")
//                .createTime(new Date())
//                .build();
//        userMapper.insert(user);
//    }


//    @Test
//    void findUserByPhone() throws Exception{
//        User user = userMapper.findUserByPhone("13851826167");
//        assertNotNull(user);
//        log.info(String.valueOf(user));
//    }

    @Test
    void updateUser() throws Exception{
        User user = userMapper.findUserByPhone("13851826167");
        user.setPassword(DigestUtils.md5Hex("123456"));
        user.setNickname("侯理豪");
        user.setAvatar("https://riddler.oss-cn-shanghai.aliyuncs.com/img/hlh.jpg");
        user.setGender(Gender.FEMALE.getKey());
        user.setBirthday(LocalDate.of(2000, 3, 23));
        user.setAddress("老八之家");
        userMapper.updateUser(user);
    }
}