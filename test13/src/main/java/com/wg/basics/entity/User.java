package com.wg.basics.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 实现UserDetails接口
 */
public class User implements UserDetails {
    private Integer id;
    private String username;
    private String password;
    private Boolean enable;
    private Boolean locked;
    private List<Role> roles;


    //得到权限列表
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles){
            authorities.add( new SimpleGrantedAuthority(role.getName()));//简单授权实例
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    //账户是否未过期（返回false抛出异常）
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //账户是否被锁（默认true），返回!locked=false;
    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }


    //密码是否未过期（false抛出异常）
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //是否被隐藏
    @Override
    public boolean isEnabled() {
        return enable;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enable=" + enable +
                ", locked=" + locked +
                ", roles=" + roles +
                '}';
    }
}
