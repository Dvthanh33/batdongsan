package com.javaweb.security.utils;

import com.javaweb.model.dto.MyUserDetail;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

public class SecurityUtils {
//lay thong tin
    public static MyUserDetail getPrincipal() {
        return (MyUserDetail) (SecurityContextHolder
                .getContext()).getAuthentication().getPrincipal();
    }
//lay role
    public static List<String> getAuthorities() {
        List<String> results = new ArrayList<>();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>)(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        for (GrantedAuthority authority : authorities) {
            results.add(authority.getAuthority());
        }
        return results;
    }
    public static boolean isManager(){
        return getAuthorities().stream()
                .anyMatch(role -> role.equals("ROLE_MANAGER"));
    }
}
