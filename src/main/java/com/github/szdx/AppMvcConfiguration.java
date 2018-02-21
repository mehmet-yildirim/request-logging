package com.github.szdx;

import com.github.szdx.filter.RequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class AppMvcConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean f = new FilterRegistrationBean();
        f.setFilter(new RequestFilter());
        f.setOrder(Ordered.LOWEST_PRECEDENCE - 8);
        return f;
    }

}
