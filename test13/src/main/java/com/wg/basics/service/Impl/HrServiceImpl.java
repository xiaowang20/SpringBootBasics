package com.wg.basics.service.Impl;

import com.wg.basics.common.CommonResult;
import com.wg.basics.dao.AdminRole;
import com.wg.basics.entity.Hr;
import com.wg.basics.entity.HrExample;
import com.wg.basics.entity.Menu;
import com.wg.basics.mapper.HrMapper;
import com.wg.basics.mapper.MenuMapper;
import com.wg.basics.service.HrService;
import com.wg.basics.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HrServiceImpl implements HrService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HrServiceImpl.class);


   private HrMapper hrMapper;
   @Autowired
     public void setHrMapper(HrMapper hrMapper){
       this.hrMapper=hrMapper;
   }
    @Autowired
    MenuMapper menuMapper;
   @Autowired
    AdminRole adminRole;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Hr getAdminByUsername(String username) {
        HrExample example = new HrExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<Hr> hrList = hrMapper.selectByExample(example);
        if (hrList != null && hrList.size() > 0) {
            return hrList.get(0);
        }
        return null;
    }


    @Override
    public List<Menu> getPermissionList(long adminId) {
        return adminRole.getAllMenus(adminId);
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateTokenByUsername(userDetails);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public Hr register(Hr adminParam) {
        Hr admin = new Hr();
        BeanUtils.copyProperties(adminParam, admin);
        admin.setEnabled(true);
        //查询是否有相同用户名的用户
        HrExample example = new HrExample();
        example.createCriteria().andUsernameEqualTo(admin.getUsername());
        List<Hr> list = hrMapper.selectByExample(example);
        if (list.size() > 0) {
            return null;
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(encodePassword);
       int i = hrMapper.insert(admin);
       if (i > 0){
           CommonResult.success("注册成功");
       }
        return admin;
    }
}
