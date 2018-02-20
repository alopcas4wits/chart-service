package de.wits.chart.config;

import de.wits.chart.controller.ChartControllerEndpointInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by alberto on 16.02.18.
 */
public class ChartConfiguration extends WebMvcConfigurerAdapter {
    @Autowired
    private ChartControllerEndpointInterceptor chartControllerEndpointInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(chartControllerEndpointInterceptor)
                .addPathPatterns("/**/pie/**/")
                .addPathPatterns("/**/bar/**/")
                .addPathPatterns("/**/linezzz/**/");
    }
}
