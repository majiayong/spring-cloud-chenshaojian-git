package com.demo.manage.web.service;

import com.demo.manage.domain.entity.Operator;
import com.demo.manage.domain.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author yangyueming
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private OperatorService operatorService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Operator user = operatorService.findByName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("UserName " + userName + " not found");
        }
        return new SecurityUser(user);
    }
}
