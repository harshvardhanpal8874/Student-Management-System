package com.example.studentManagement.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(1)
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                          ServletResponse response,
                          FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;


        System.out.println("AuthenticationFilter: " + httpRequest.getRequestURI());

        chain.doFilter(request, response);
    }
}
