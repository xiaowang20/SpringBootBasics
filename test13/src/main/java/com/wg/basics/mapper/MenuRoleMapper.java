package com.wg.basics.mapper;

import com.wg.basics.entity.MenuRole;
import com.wg.basics.entity.MenuRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MenuRoleMapper {
    long countByExample(MenuRoleExample example);

    int deleteByExample(MenuRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MenuRole record);

    int insertSelective(MenuRole record);

    List<MenuRole> selectByExample(MenuRoleExample example);

    MenuRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MenuRole record, @Param("example") MenuRoleExample example);

    int updateByExample(@Param("record") MenuRole record, @Param("example") MenuRoleExample example);

    int updateByPrimaryKeySelective(MenuRole record);

    int updateByPrimaryKey(MenuRole record);
}