package com.demo.manage.web.service;

import com.demo.manage.domain.entity.Operator;
import com.demo.manage.domain.entity.Part;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author yangyueming
 */
public class SecurityUser extends Operator implements UserDetails {

    private static final long serialVersionUID = 1L;

    public SecurityUser(Operator user) {
        if (user != null) {
            this.setId(user.getId());
            this.setName(user.getName());
            this.setEmail(user.getEmail());
            this.setPassword(user.getPassword());
            this.setSex(user.getSex());
            this.setCreated(user.getCreated());
            this.setParts(user.getParts());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        for (Part role : this.getParts()) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.getName());
            grantedAuthorityList.add(simpleGrantedAuthority);
        }
        return grantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getName();
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
        return true;
    }

}
