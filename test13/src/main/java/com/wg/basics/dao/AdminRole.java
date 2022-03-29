package com.wg.basics.dao;

import com.wg.basics.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminRole {

    /**
     * 获取资源
     * @param adminId
     * @return
     */
    List<Menu> getAllMenus(@Param("adminId") long adminId);
}
