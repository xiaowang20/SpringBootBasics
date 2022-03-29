package com.wg.basics.security.vo;

import com.wg.basics.entity.Hr;
import com.wg.basics.entity.Menu;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * security需要的用户详情
 */
@Data
public class AdminUserDetails implements UserDetails {

    private Hr hr;
    private List<Menu> menu;

    public AdminUserDetails(Hr hr, List<Menu> menu) {
        this.hr = hr;
        this.menu = menu;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return menu.stream()
                .filter(menu1 -> menu1.getPath()!=null)
                .map(menu1 -> new SimpleGrantedAuthority(menu1.getPath()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return hr.getPassword();
    }

    @Override
    public String getUsername() {
        return hr.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return hr.getEnabled().equals(1);
    }
}
