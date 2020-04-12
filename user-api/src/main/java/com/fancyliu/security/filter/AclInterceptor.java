package com.fancyliu.security.filter;

import com.fancyliu.security.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AclInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(4);
        boolean result = true;

        User user = (User) request.getAttribute("user");

        // 简单处理,对所有请求都进行验证
        if (user == null) {
            response.setContentType("text/plain");
            response.getWriter().write("no permission");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            result = false;
        } else {
            String method = request.getMethod();

            if (!user.hasPermission(method)){
                response.setContentType("text/plain");
                response.getWriter().write("forbidden");
                response.setStatus(HttpStatus.FORBIDDEN.value());
                result = false;
            }
        }

        return result;
    }
}
