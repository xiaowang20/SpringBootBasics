package com.wg.basics.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Hr实体类
 */
@Data
public class Hr{
    private Integer id;
    private String name;//姓名
    private String phone;//手机号码
    private String telephone;//住宅号码
    private String address;
    private Boolean enabled;
    private String username;//用户名
    private String password;
    private String userface;//头像
    private String remark;//评论

}
