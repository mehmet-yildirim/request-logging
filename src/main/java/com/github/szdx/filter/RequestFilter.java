package com.github.szdx.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class RequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        log.info("Entered to filter...");

        BufferedServletRequestWrapper wrapper = new BufferedServletRequestWrapper(httpServletRequest);

        log.info("Buffer is: " + wrapper.getBody());

        filterChain.doFilter(wrapper, httpServletResponse);

        log.info("Exited from filter...");
    }
}
