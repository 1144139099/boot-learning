package com.hlh.Mybts.mybatis.mapper;

import com.hlh.Mybts.mybatis.domain.Teacher;

/**
* @author hlh
* @description 针对表【t_teacher】的数据库操作Mapper
* @createDate 2022-03-28 17:25:58
* @Entity com.hlh.Mybts.mybatis.domain.Teacher
*/
public interface TeacherMapper {
    Teacher findOneByOne(Integer id);

}




