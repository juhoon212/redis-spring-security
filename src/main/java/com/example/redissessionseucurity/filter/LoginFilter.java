package com.example.redissessionseucurity.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.session.web.http.HttpSessionIdResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.stream.Collectors;

@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper;


    public LoginFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        setFilterProcessesUrl("/login");
        setPostOnly(true);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = null;
        String rawPassword = null;

        try {
            final LoginRequest loginRequest = objectMapper.readValue(
                    request.getReader().lines().collect(Collectors.joining()), LoginRequest.class);

            log.info("username = {}", loginRequest.getLoginId());
            log.info("pw = {}", loginRequest.getPassword());

            final HttpSession session = request.getSession(true);
            log.info(session.getId());
        } catch (IOException e) {
            log.error("", e);
        }

        return null;
    }
}
