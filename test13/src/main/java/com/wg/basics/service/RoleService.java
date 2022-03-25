package com.wg.basics.service;

import com.wg.basics.entity.Role;
import com.wg.basics.mapper.RoleMapper;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    final RoleMapper roleMapper;
    RoleService(RoleMapper roleMapper){
        this.roleMapper=roleMapper;
    }

    public int addRole(Role role){

        return roleMapper.addRole(role);
    }
}
