package com.wg.basics.mapper;

import com.wg.basics.entity.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {
    int addRole(Role role);
}
