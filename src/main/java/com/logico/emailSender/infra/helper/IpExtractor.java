package com.logico.emailSender.infra.helper;

import jakarta.servlet.http.HttpServletRequest;

public class IpExtractor {
    public static String extractClientIp(HttpServletRequest request) {
        String forwarded = request.getHeader("X-Forwarded-For");
        if (forwarded != null && !forwarded.isEmpty()) {
            return forwarded.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }
}
