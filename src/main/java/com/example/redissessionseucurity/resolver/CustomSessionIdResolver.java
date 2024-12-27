package com.example.redissessionseucurity.resolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
public class CustomSessionIdResolver extends HeaderHttpSessionIdResolver {
    public CustomSessionIdResolver(String headerName) {
        super(headerName);
    }

    @Override
    public List<String> resolveSessionIds(HttpServletRequest request) {
        final List<String> sessionIds = super.resolveSessionIds(request);
        if (sessionIds.isEmpty()) {
            String sessionId = request.getHeader("X-AUTH-TOKEN");
            if (sessionId != null) {
                sessionIds.add(sessionId);
            }
        }

        return sessionIds;
    }

    @Override
    public void setSessionId(HttpServletRequest request, HttpServletResponse response, String sessionId) {
        super.setSessionId(request, response, sessionId);
        response.setHeader("X-AUTH-TOKEN", sessionId);
    }
}
