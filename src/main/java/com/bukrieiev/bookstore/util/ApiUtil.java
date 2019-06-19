package com.bukrieiev.bookstore.util;

import com.bukrieiev.bookstore.entity.RoleName;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.Collection;

public class ApiUtil {

    public static RoleName getRoleFromAuthorities(Collection<? extends GrantedAuthority> authorities) {
        String role = String.valueOf(Arrays.stream(authorities.toArray()).findFirst().get());
        return RoleName.valueOf(role);
    }
}
