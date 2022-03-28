package com.hlh.Mybts.mybatis.mapper;

import com.hlh.Mybts.mybatis.domain.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author hlh
* @description 针对表【t_student】的数据库操作Mapper
* @createDate 2022-03-28 15:22:49
* @Entity com.hlh.Mybts.generator.domain.Student
*/
public interface StudentMapper {
    Student findManyByOne(int studentId);

    Student getStudent(int studentId);

    int batchInsert(@Param("students") List<Student> students);

    int batchDelete(@Param("ids") List<Integer> ids);

    int batchUpdate(@Param("students") List<Student> students);

    List<Student> findStudentBy(@Param("student") Student student);

}




