package de.wits.chart.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ChartControllerEndpointInterceptor extends HandlerInterceptorAdapter {

    protected static final Logger LOG = LoggerFactory.getLogger(ChartControllerEndpointInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOG.info("Request in! " + request.getContextPath());
        return super.preHandle(request, response, handler);
    }
}