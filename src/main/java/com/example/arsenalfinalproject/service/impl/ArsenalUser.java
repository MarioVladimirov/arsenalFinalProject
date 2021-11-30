package com.example.arsenalfinalproject.service.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class ArsenalUser extends User {
    public ArsenalUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public ArsenalUser(String username, String password, boolean enabled, boolean accountNonExpired,
                       boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }


    public String getUserIdentifier() {
        return getUsername();
    }

    public String getPasswordIdentifier() {
        return getPassword();
    }


}
