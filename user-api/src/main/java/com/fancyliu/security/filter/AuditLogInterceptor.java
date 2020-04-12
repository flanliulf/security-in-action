package com.fancyliu.security.filter;

import com.fancyliu.security.log.AuditLog;
import com.fancyliu.security.log.AuditLogRepository;
import com.fancyliu.security.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuditLogInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private AuditLogRepository auditLogRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(3);

        AuditLog log = new AuditLog();
        log.setMethod(request.getMethod());
        log.setPath(request.getRequestURI());

        auditLogRepository.save(log);

        request.setAttribute("auditlogId", log.getId());

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Long auditlogId = (Long) request.getAttribute("auditlogId");

        AuditLog auditLog = auditLogRepository.findById(auditlogId).get();
        auditLog.setStatus(response.getStatus());

        auditLogRepository.save(auditLog);
    }
}
