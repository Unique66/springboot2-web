package com.sxh.springboot2web.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author SXH
 * @description 接口入参各种注解测试
 * @date 2021/1/14 0:19
 */
@RestController
public class ParameterTestController {
    /**
     * @PathVariable        路径变量
     * @RequestHeader       获取请求头
     * @RequestParam        获取请求参数
     * @CookieValue         获取cookie 值
     * @RequestBody         获取请求体（POST请求的）
     * @RequestAttribute    获取request 域属性
     */
    @GetMapping("/parameter/{id}/owner/{owner}")
    public Map<String, Object> testParameterAnnotation(
            @PathVariable("id") String id, @PathVariable("owner") String owner,
            @PathVariable Map<String, String> pv, // 约定的Map<String, String> 获取所有@PathVariable
            @RequestHeader("Accept-Language") String header1,
            @RequestHeader Map<String, String> header, // 约定的Map<String, String> 获取所有@RequestParam
            @RequestParam(value = "param1", required = false) String param1,
            @RequestParam(value = "param2", required = false) String param2,
            @RequestParam(value = "listParam", required = false)List<String> listParam,
            @RequestParam Map<String, String> params,
            @CookieValue("bdshare_firstime") String cookie1,
            @CookieValue("bdshare_firstime") Cookie cookie
    ) {
        Map<String, Object> map = new TreeMap<>();
        // 测试@PathVariable  http://localhost:8080/parameter/1/owner/sxh
        map.put("id", id);
        map.put("owner", owner);
        map.put("pv", pv);
        // 测试@RequestHeader
        map.put("header1", header1);
//        map.put("requestHead", header); // 所有header，很多
        // 测试@RequestParam
        // http://localhost:8080/parameter/1/owner/sxh?param1=p1&param2=p2&listParam=li1&listParam=lis2
        map.put("param1", param1);
        map.put("param2", param2);
        map.put("listParam", listParam);
        map.put("params", params);
        // 测试@CookieValue
        map.put("cookie1:bdshare_firstime:", cookie1);
        map.put("cookie", cookie.getName());
        return map;
    }

    @PostMapping("/parameter/save")
    public Map<String, Object> postMethod(
        @RequestBody String content
    ) {
        Map<String, Object> map = new TreeMap<>();
        // index.html 表单发送POST 请求
        map.put("content", content);
        return map;
    }

    /**
     * @MatrixVariable      矩阵变量
     * 1、 /parameter/sell;low=1;brand=brand1,brand2,brand3     sell 为自定义路径{path}，可传任意值
     * 2、 SpringBoot 默认禁用了矩阵变量的功能
     *      需要手动开启：原理：对于路径的处理  URLPathHelper 进行解析
     *          removeSemicolonContent 移除分号内容，支持矩阵变量
     */
    @GetMapping("/parameter/{path}")
    public Map<String, Object> testMatrixVariable1(
           @MatrixVariable("low") Integer low,
           @MatrixVariable("brand") List<String> brand,
           @PathVariable("path") String path
    ) {
        Map<String, Object> map = new TreeMap<>();
        map.put("low", low);
        map.put("brand", brand);
        map.put("path", path);
        return map;
    }

    // /boss/1;age=20/2;age=10
    @GetMapping("/boss/{bossId}/{empId}")
    public Map<String, Object> testMatrixVariable2(
            @MatrixVariable(value = "age", pathVar = "bossId") Integer bossAge,
            @MatrixVariable(value = "age", pathVar = "empId") Integer empAge,
            @PathVariable("bossId") String bossId,
            @PathVariable("empId") String empId
    ) {
        Map<String, Object> map = new TreeMap<>();
        // http://localhost:8080/boss/1;age=20/2;age=10
        map.put("bossId", bossId);
        map.put("empId", empId);
        map.put("bossId_age", bossAge);
        map.put("empId_age", empAge);
        return map;
    }
}
