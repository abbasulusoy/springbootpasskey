package com.example.demo.component;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DebugFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;

        System.out.println("===== DEBUG REQUEST =====");
        System.out.println("URI: " + request.getRequestURI());
        System.out.println("Method: " + request.getMethod());
        System.out.println("Content-Type: " + request.getContentType());
        System.out.println("username param: " + request.getParameter("username"));

        chain.doFilter(req, res);
    }
}