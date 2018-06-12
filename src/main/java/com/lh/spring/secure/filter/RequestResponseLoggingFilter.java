package com.lh.spring.secure.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class RequestResponseLoggingFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) servletRequest);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) servletResponse);

        log.info("current unix Time:" + System.currentTimeMillis() + " Logging Request  {} : {} , param :{}", requestWrapper.getMethod(),
                requestWrapper.getRequestURI(), getRequestParamOfGetToken(requestWrapper));
        filterChain.doFilter(requestWrapper, responseWrapper);
        byte[] responseBodyBytes = responseWrapper.getContentAsByteArray();
        log.info("current unix Time:" + System.currentTimeMillis() + " Logging Response {} {} body: {}",requestWrapper.getRequestURI(), responseWrapper.getStatusCode(), new String(responseBodyBytes));
        responseWrapper.copyBodyToResponse();
    }

    private String getRequestParamOfGetToken(HttpServletRequest request) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            builder.append(entry.getKey() + ":" + Arrays.toString(entry.getValue()) + ", ");
        }
        builder.delete(builder.length() - 2, builder.length());
        return builder.toString();
    }

    @Override
    public void destroy() {

    }
}
