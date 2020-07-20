package com.dm.utils.web;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author hu.yuhao
 * 获取当前请求与响应
 * */
public class ServletUtils {
    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return ((ServletRequestAttributes)requestAttributes).getRequest();
    }

    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
    }

    public static void render(String msg) throws IOException {
        HttpServletResponse response = getResponse();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.print(msg);
    }

    public static void renderString(HttpServletResponse response, String msg) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.print(msg);
    }
}
