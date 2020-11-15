package com.eziozhao.leafblog.bo;

import com.eziozhao.leafblog.mbg.entity.Resource;
import com.eziozhao.leafblog.mbg.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author eziozhao
 * @date 2020/6/6
 */
@Getter
public class MyUserDetails implements UserDetails {
    private User user;
    private List<Resource> resources;

    public MyUserDetails(User user, List<Resource> resources) {
        this.user = user;
        this.resources = resources;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return resources.stream().map(el -> new SimpleGrantedAuthority(el.getId() + ":" + el.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
        return user.getEnabled()==1;
    }
}
