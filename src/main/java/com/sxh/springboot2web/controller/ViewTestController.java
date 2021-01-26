package com.sxh.springboot2web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SXH
 * @description 测试Thymeleaf 功能，关注ThymeleafAutoConfiguration
 * @date 2021/1/26 23:25
 */
@Controller
public class ViewTestController {
    @GetMapping("/testThymeleaf")
    public String testThymeleaf(Model model) {
        // model 中的数据会被放到请求域中 request.setAttribute("a", "1") 类似
        model.addAttribute("msg", "你好 thymeleaf。");
        model.addAttribute("link", "http://www.baidu.com");
        model.addAttribute("name", "无标签时的写法");
        /*
         * ThymeleafProperties 中默认配置了前缀和后缀，所以只用返回HTML 名字即可
         *     public static final String DEFAULT_PREFIX = "classpath:/templates/";
         *     public static final String DEFAULT_SUFFIX = ".html";
         */
        return "success";
    }
}
