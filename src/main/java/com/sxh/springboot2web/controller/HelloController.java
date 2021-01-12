package com.sxh.springboot2web.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author SXH
 * @description HelloController
 * @date 2021/1/12 22:40
 */
@RestController
public class HelloController {
    /**
     * 静态资源默认配置在resources下的四个目录
     * public、resources、META-INF.resources、static
     * 可以使用http://localhost:8080/struggle1.png 的请求访问静态资源
     * 先访问动态资源(如果代码里有Controller 配置了该请求)，那么会返回该controller 的结果
     * 没有匹配到动态资源，就会到静态资源中寻找
     *
     * 静态资源默认的四个目录可以通过配置文件调整，过滤方式可以加上前缀，以区分动态资源和静态资源
     */
    @GetMapping("/struggle.png")
    public String testStaticResources() {
        return "testStaticResources";
    }

    @GetMapping("/user")
    public String getUser() {
        return "GET USER";
    }

    @PostMapping("/user")
    public String postUser() {
        return "POST USER";
    }

    @DeleteMapping("/user")
    public String deleteUser() {
        return "DELETE USER";
    }

    @PutMapping("/user")
    public String putUser() {
        return "PUT USER";
    }
}
