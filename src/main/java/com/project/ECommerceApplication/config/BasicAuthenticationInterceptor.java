package com.project.ECommerceApplication.config;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Base64;

@Component
public class BasicAuthenticationInterceptor implements HandlerInterceptor {
    private static Dotenv dotenv = Dotenv.load();
    private static final String AUTH_HEADER = "Authorization";
    private static final String AUTH_PREFIX = "Basic ";
    private static final String EXPECTED_USERNAME = dotenv.get("ADMIN");
    private static final String EXPECTED_PASSWORD = dotenv.get("PASSWORD");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader(AUTH_HEADER);

        if (authHeader == null || !authHeader.startsWith(AUTH_PREFIX)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader("WWW-Authenticate", "Basic realm=\"Restricted Access\"");
            return false;
        }

        String base64Credentials = authHeader.substring(AUTH_PREFIX.length());
        String credentials = new String(Base64.getDecoder().decode(base64Credentials));
        String[] values = credentials.split(":", 2);

        if (values.length != 2 || !values[0].equals(EXPECTED_USERNAME) || !values[1].equals(EXPECTED_PASSWORD)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        return true;
    }


}
