package com.lacosdaalegria.transparencia.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public abstract class SecurityUtil {
	
	public static String loggedUser(){
        if(getUserDetail() == null)
            return null;
        return getUserDetail().getUsername();
    }

    private static UserDetails getUserDetail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null)
            return null;
        else
            return (UserDetails) authentication.getPrincipal();
    }

}
