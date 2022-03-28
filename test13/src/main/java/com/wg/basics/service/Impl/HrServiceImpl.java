package com.wg.basics.service.Impl;

import com.wg.basics.entity.Hr;
import com.wg.basics.entity.Menu;
import com.wg.basics.mapper.HrMapper;
import com.wg.basics.mapper.MenuMapper;
import com.wg.basics.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HrServiceImpl implements HrService {

    @Autowired
    HrMapper hrMapper;
    @Autowired
    MenuMapper menuMapper;
    @Override
    public Hr getAdminByUsername(String username) {
        Hr admin = hrMapper.getAdminByUsername(username);
        return admin;
    }


    @Override
    public List<Menu> getPermissionList(long adminId) {
        return menuMapper.getAllMenus(adminId);
    }
}
