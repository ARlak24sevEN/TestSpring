package com.example.backend.config.token;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.backend.service.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TokenFilter extends GenericFilter {

    private final TokenService tokenService;

    public TokenFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //check header have authorization
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String authorization = request.getHeader("Authorization");

        if (ObjectUtils.isEmpty(authorization)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;

            //Check if Authorization isEmpty ก็ปล่อยไป
        }

        if (!authorization.startsWith("Bearer ")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
            //authorization not start with Bearer ก็ปล่อยไป
        }

        String token = authorization.substring(7); //=>set value after 7 is a token

        DecodedJWT decoded = tokenService.verify(token);
        if (decoded == null) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
            //decoded ไม่ผ่านจะส่งกลับมาเป็นค่า null
        }

        //user id
        String principal = decoded.getClaim("principal").asString();
        String role = decoded.getClaim("role").asString();
        List<GrantedAuthority> authority = new ArrayList<>();
        authority.add(new SimpleGrantedAuthority(role));

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principal, "(protected)", authority);

        //set Authorization spring
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authentication);

        filterChain.doFilter(servletRequest,servletResponse);
    }
}
