package com.dm.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("init auth filter");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
        if (!"/test/auth".equals(path)) {
            HttpSession session = request.getSession();
            String userID = (String)session.getAttribute("userID");

            if ((userID == null || "".equals(userID)) && !"/test/unauth".equals(path)) {
                response.sendRedirect(request.getContextPath()+"/test/unauth");
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
