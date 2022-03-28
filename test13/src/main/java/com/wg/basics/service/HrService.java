package com.wg.basics.service;

import com.wg.basics.entity.Hr;
import com.wg.basics.entity.Menu;

import java.util.List;

public interface HrService {
    /**
     * 根据用户名获取后台管理员
     */
    Hr getAdminByUsername(String username);

    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     */
    List<Menu> getPermissionList(long adminId);
}
