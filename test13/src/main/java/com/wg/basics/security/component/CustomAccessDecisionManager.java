package com.wg.basics.security.component;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 实现AccessDecisionManager（拒绝访问管理器）
 * 自定义角色信息对比
 * 通过知道资源需要哪些角色后，再判断当前登录的用户是否具备当前请求 URL 所需要的角色信息。
 */
@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {

    /**
     *
     * @param auth
     * @param object
     * @param configAttributes
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication auth,
                       Object object,
                       Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        //获取认证信息
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        //遍历配置属性
        for (ConfigAttribute configAttribute : configAttributes) {
            //比较角色信息，如果是ROLE_LOGIN，用户登录后访问。如果是UsernamePasswordAuthenticationToken实例，表示已登录
            if ("ROLE_LOGIN".equals(configAttribute.getAttribute())
                    && auth instanceof UsernamePasswordAuthenticationToken){
                return;
            }
            //遍历角色信息
            for (GrantedAuthority authority : authorities) {
                //如果认证信息一致，继续执行。
                if (authority.getAuthority().equals(configAttribute.getAttribute())){
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
