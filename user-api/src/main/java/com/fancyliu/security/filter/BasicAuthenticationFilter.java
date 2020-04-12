package com.fancyliu.security.filter;

import com.fancyliu.security.user.User;
import com.fancyliu.security.user.UserRepository;
import com.lambdaworks.crypto.SCryptUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(2)
public class BasicAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserRepository userRepository;


    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println(2);
        String authorization = request.getHeader("Authorization");

        if (StringUtils.isNotBlank(authorization)) {
            String token64 = StringUtils.substringAfter(authorization, "Basic ");
            String token = new String(Base64Utils.decodeFromString(token64));
            String[] tokens = StringUtils.splitByWholeSeparatorPreserveAllTokens(token, ":");

            String username = tokens[0];
            String password = tokens[1];

            User user = userRepository.findByUsername(username);
            if (user != null && SCryptUtil.check(password, user.getPassword())) {
                request.setAttribute("user", user);
            }
        }

        filterChain.doFilter(request, response);

    }
}
