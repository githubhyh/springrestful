package com.dm.config;

import com.dm.filter.AuthFilter;
import com.dm.filter.SessionFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;
import java.util.Arrays;

//@Configuration
public class SpringFilterConfig {

    //@Bean
    public AuthFilter authFilter() {
        return new AuthFilter();
    }

    //@Bean
    public SessionFilter customSessionFilter() {
        return new SessionFilter();
    }

    //@Bean
    public FilterRegistrationBean authFilterRegister() {
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<AuthFilter>();
        registrationBean.setFilter(authFilter());
        registrationBean.setName("authFilter");
        registrationBean.setDispatcherTypes(DispatcherType.REQUEST);
        registrationBean.setUrlPatterns(Arrays.asList("/*"));
        return registrationBean;
    }

    //@Bean
    public FilterRegistrationBean sessionFilter() {
        FilterRegistrationBean<SessionFilter> registrationBean = new FilterRegistrationBean<SessionFilter>();
        registrationBean.setFilter(customSessionFilter());
        registrationBean.setName("sessionFilter");
        registrationBean.setDispatcherTypes(DispatcherType.REQUEST);
        registrationBean.setUrlPatterns(Arrays.asList("/*"));
        registrationBean.setOrder(0);
        return registrationBean;
    }
}
