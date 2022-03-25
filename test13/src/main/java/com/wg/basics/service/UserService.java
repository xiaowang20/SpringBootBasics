package com.wg.basics.service;

import com.wg.basics.entity.User;
import com.wg.basics.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.print.Book;

@Service
public class UserService implements UserDetailsService {

    final UserMapper userMapper;
    UserService(UserMapper userMapper){
        this.userMapper=userMapper;
    }
    public int register(){
        User user = new User();
        user.setUsername("sang");
        user.setPassword(new BCryptPasswordEncoder().encode("123456"));
        user.setEnable(true);
        user.setLocked(false);
        return userMapper.register(user);
    }

    public User getUser(String userName){
        return userMapper.getUser(userName);
    }

    public int updateUserId(){
        User admin = getUser("sang");
        admin.setId(3);

        return userMapper.updateUserId(admin);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getUser(username);
        if (user == null){
            throw new UsernameNotFoundException("账户不存在");
        }
        user.setRoles(userMapper.getUserRoleById(user.getId()));
        return user;
    }
}
