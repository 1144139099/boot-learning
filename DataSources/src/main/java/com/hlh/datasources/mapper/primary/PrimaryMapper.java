package com.hlh.datasources.mapper.primary;

import com.hlh.datasources.entity.BootUser;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface PrimaryMapper {
    List<BootUser> selectAll();
}
