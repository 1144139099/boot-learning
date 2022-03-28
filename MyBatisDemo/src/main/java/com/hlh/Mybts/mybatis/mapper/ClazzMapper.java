package com.hlh.Mybts.mybatis.mapper;

import com.hlh.Mybts.mybatis.domain.Clazz;

/**
* @author hlh
* @description 针对表【t_clazz】的数据库操作Mapper
* @createDate 2022-03-28 15:22:49
* @Entity com.hlh.Mybts.generator.domain.Clazz
*/
public interface ClazzMapper {
    Clazz findOneByMany(int clazzId);

    Clazz getClazz(int clazzId);

}




