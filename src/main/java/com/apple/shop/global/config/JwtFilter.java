package com.apple.shop.global.config;

import com.apple.shop.domain.member.service.MyUserDetailsService;
import com.apple.shop.domain.member.service.MyUserDetailsService.CustomUser;
import com.apple.shop.global.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.sql.Array;
import java.util.Arrays;

public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
      FilterChain filterChain
  ) throws ServletException, IOException {


    //요청들어올때마다 실행할코드~~
        Cookie[] cookies = request.getCookies();
        Cookie cookie=null;
        if (cookies==null) filterChain.doFilter(request, response);

        for(int i=0; i<cookies.length; i++){
            if(cookies[i].getName().equals("jwt")){
               cookie=cookies[i];
            }
        }

        Claims claim;
        try{
            claim=JwtUtil.extractToken(cookie.getValue());
        }catch (Exception e){
            System.out.println("(Error) "+e.getMessage());
            filterChain.doFilter(request, response);
            return;
        }

        String[] arr = claim.get("authorities").toString().split(",");
        var authorites=Arrays.stream(arr)
                .map(o -> new SimpleGrantedAuthority(o)).toList();

        CustomUser customUsr = new CustomUser(
                claim.get("username").toString(),
                "",
                authorites
        );
        customUsr.displayName=claim.get("displayName").toString();

        var authToken = new UsernamePasswordAuthenticationToken(
                customUsr, "", authorites
        );

        authToken.setDetails(new WebAuthenticationDetailsSource()
                .buildDetails(request)
        );
        SecurityContextHolder.getContext().setAuthentication(authToken);


        filterChain.doFilter(request, response);    // 다음 필터로 넘어가주세요.
  }


}
