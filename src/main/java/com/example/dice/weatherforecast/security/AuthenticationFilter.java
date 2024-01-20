package com.example.dice.weatherforecast.security;

import com.example.dice.weatherforecast.entity.ErrorEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.http.HttpResponse;

@Component
public class AuthenticationFilter implements Filter {

    private static final String CLIENT_ID_HEADER = "client-id";
    private static final String CLIENT_SECRET_HEADER = "client-secret";
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String clientId = ((HttpServletRequest)servletRequest).getHeader(CLIENT_ID_HEADER);
        String clientSecret =  ((HttpServletRequest)servletRequest).getHeader(CLIENT_SECRET_HEADER);

        if(isValidCredentials(clientId,clientSecret)){
            filterChain.doFilter(servletRequest,servletResponse);
        }
        else{
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            ErrorEntity errorEntity = new ErrorEntity("Please Provide Valid Client ID and Secret Key for Authentication","UN_AUTHORIZED","401");
            String json = new ObjectMapper().writeValueAsString(errorEntity);
            httpServletResponse.getWriter().write(json);
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            httpServletResponse.flushBuffer();
        }
    }

    private boolean isValidCredentials(String clientId, String clientSecret){
        return clientId!=null && clientSecret!=null && !clientId.isEmpty() && !clientSecret.isEmpty();
    }
}
