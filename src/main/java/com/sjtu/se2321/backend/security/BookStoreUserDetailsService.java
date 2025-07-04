package com.sjtu.se2321.backend.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sjtu.se2321.backend.dao.UserDAO;
import com.sjtu.se2321.backend.entity.User;

@Service
public class BookStoreUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userDAO.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        if (user.getIsBanned() != null && user.getIsBanned()) {
            throw new DisabledException("账号已被禁用，请联系管理员");
        }

        return new org.springframework.security.core.userdetails.User(
                username, user.getUserAuth().getPassword(), getAuthority(user));
    }

    private Collection<? extends GrantedAuthority> getAuthority(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        if (user.getRole() == User.Role.ADMIN) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return authorities;
    }

}
