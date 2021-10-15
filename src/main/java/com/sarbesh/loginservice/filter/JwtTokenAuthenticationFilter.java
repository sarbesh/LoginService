package com.sarbesh.loginservice.filter;

import com.sarbesh.core.filter.CustomJwtFilterUPAT;
import com.sarbesh.core.security.JwtHelper;
import com.sarbesh.loginservice.service.impl.LoginUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenAuthenticationFilter implements CustomJwtFilterUPAT {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenAuthenticationFilter.class);

    private JwtHelper jwtHelper;

    private LoginUserDetailsService loginUserDetailsService;

    @Autowired
    public JwtTokenAuthenticationFilter(JwtHelper jwtHelper, LoginUserDetailsService loginUserDetailsService) {
        this.jwtHelper = jwtHelper;
        this.loginUserDetailsService = loginUserDetailsService;
    }

    @Override
    public UsernamePasswordAuthenticationToken setUPAT(String jwt, String username) throws Exception {
        UserDetails userDetails = loginUserDetailsService.loadUserByUsername(username);
        if (jwtHelper.validateToken(jwt, userDetails)) {
            //                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            return jwtHelper.getAuthenticationToken(jwt, SecurityContextHolder.getContext().getAuthentication(), userDetails);
        } else {
            LOGGER.error("#JwtTokenAuthenticationFilter Invalid token");
            throw new Exception("Token Invalid");
        }
    }
}
