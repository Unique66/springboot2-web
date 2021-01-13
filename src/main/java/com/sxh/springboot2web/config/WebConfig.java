package com.sxh.springboot2web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

/**
 * @author SXH
 * @description 自定义web 配置
 * @date 2021/1/13 23:05
 */
@Configuration
public class WebConfig {
    /**
     * 自定义表单RESTFul 请求的过滤配置
     */
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        // 将默认配置中的_method 改为m 自定义
        hiddenHttpMethodFilter.setMethodParam("_m");
        return hiddenHttpMethodFilter;
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        // 不移除分号后面的内容，保证矩阵变量的功能实现
        urlPathHelper.setRemoveSemicolonContent(false);
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {
                configurer.setUrlPathHelper(urlPathHelper);
            }
        };
        return webMvcConfigurer;
    }
}
