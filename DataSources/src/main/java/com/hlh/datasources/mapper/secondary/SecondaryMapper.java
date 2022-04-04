package com.hlh.datasources.mapper.secondary;

import com.hlh.datasources.entity.BootUser;

import java.util.List;

public interface SecondaryMapper {
    List<BootUser> selectAll();
}
