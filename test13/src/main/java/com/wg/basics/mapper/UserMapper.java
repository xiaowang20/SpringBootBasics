package com.wg.basics.mapper;

import com.wg.basics.entity.Role;
import com.wg.basics.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 注册
     * @param user
     * @return
     */
    int register(User user);

    /**
     * 获取用户
     * @param userName
     * @return
     */
    User getUser(String userName);

    /**
     * 修改用户id
     * @param user
     * @return
     */
    int updateUserId(User user);

    /**
     * 获得角色
     * @param id
     * @return
     */
    List<Role> getUserRoleById(Integer id);
}
