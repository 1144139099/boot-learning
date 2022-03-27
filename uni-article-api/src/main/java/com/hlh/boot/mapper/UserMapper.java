package com.hlh.boot.mapper;

import com.hlh.boot.model.entity.User;
import org.apache.ibatis.annotations.*;

public interface UserMapper {
    @Insert("INSERT INTO t_user (phone,wx_openid,password,nickname,avatar,gender,birthday,address,create_time) " +
    "VALUES (#{phone}, #{wxOpenId},#{password}, #{nickname}, #{avatar}, #{gender}, #{birthday}, #{address}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    @Select("SELECT * FROM t_user WHERE phone = #{phone} ")
    User findUserByPhone(@Param("phone") String phone);

    @Update("UPDATE t_user SET password=#{password},nickname=#{nickname},avatar=#{avatar}," +
    "gender=#{gender},birthday=#{birthday},address=#{address} WHERE id=#{id} ")
    void updateUser(User user);

    @Select("SELECT * FROM t_user WHERE wx_openId = #{wxOpenId} ")
    User fineUserByOpenId(@Param("wxOpenId") String wxOpenId);

    @Update(("UPDATE t_user SET wx_openid=#{wxOpenId} WHERE phone=#{phone} "))
    void bandPhone(@Param("phone") String phone, @Param("wxOpenId") String wxOpenId);

    @Delete(("DELETE FROM t_user WHERE wx_openid=#{wxOpenId} "))
    void deleteUserByOpenId(@Param("wxOpenId") String wxOpenId);
}
