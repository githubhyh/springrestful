package com.dm.filter;

import com.dm.wrapper.DMHttpServletRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SessionFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(SessionFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("init session filter");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("swap http servlet request");
        filterChain.doFilter(new DMHttpServletRequestWrapper((HttpServletRequest)servletRequest), servletResponse);
    }
}
