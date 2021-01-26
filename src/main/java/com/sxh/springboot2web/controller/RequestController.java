package com.sxh.springboot2web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author SXH
 * @description 测试@RequestAttribute
 * @date 2021/1/14 1:05
 */
@Controller
public class RequestController {
    /**
     * @RequestAttribute    获取request 域属性
     */
    @GetMapping("/goto")
    public String gotoPage(HttpServletRequest request) {
        request.setAttribute("msg", "Success!");
        request.setAttribute("code", 200);
        return "forward:/success"; // 转发到请求 /success
    }

    @ResponseBody
    @GetMapping("/success")
    public Map<String, Object> success(
            @RequestAttribute(value = "msg" ,required = false) String msg,
            @RequestAttribute(value = "code", required = false) Integer code,
            HttpServletRequest request
    ) {
        Map<String, Object> map = new TreeMap<>();
        // http://localhost:8080/goto
        map.put("msg", msg);
        map.put("code", code);

        map.put("HttpServletRequest_msg", request.getAttribute("msg"));
        map.put("HttpServletRequest_code", request.getAttribute("code"));
        return map;
    }
}
