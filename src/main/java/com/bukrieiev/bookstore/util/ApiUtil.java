package com.bukrieiev.bookstore.util;

import com.bukrieiev.bookstore.entity.RoleName;
import com.bukrieiev.bookstore.entity.User;
import com.bukrieiev.bookstore.security.UserPrincipal;
import com.bukrieiev.bookstore.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.Collection;

public class ApiUtil {

    private static UserService userService;

    public static void setUserService(UserService userService) {
        ApiUtil.userService = userService;
    }

    public static RoleName getRoleFromAuthorities(Collection<? extends GrantedAuthority> authorities) {
        String role = String.valueOf(Arrays.stream(authorities.toArray()).findFirst().orElse(null));
        return RoleName.valueOf(role);
    }

    public static User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
        return userService.getUserWithLoadedUserInf(principal.getId());
    }
}
