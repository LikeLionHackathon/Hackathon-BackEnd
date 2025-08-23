package com.Hackathon.generic.login.interceptor;

import com.Hackathon.generic.login.auth.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        // ✅ /api/v1/users 경로 중 POST 요청만 통과
        if ("/api/v1/users".equals(requestURI) && "POST".equalsIgnoreCase(method)) {
            return true;
        }
        Object loginUser = request.getSession(false) == null ? null
            : request.getSession(false).getAttribute(AuthService.LOGIN_SESSION_KEY);
        if (loginUser == null) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"error\":\"UNAUTHORIZED\"}");
            return false;
        }
        return true; // 통과
    }
}
