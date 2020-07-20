package com.dm.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

public class DMHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private HttpServletRequest request;

    public DMHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    @Override
    public HttpSession getSession() {
        HttpSession session = super.getSession();
        session.setAttribute("userID", "test user id");
        return session;
    }

    @Override
    public HttpSession getSession(boolean create) {
        return super.getSession(create);
    }
}
