package com.zjj.controller.async;

import com.zjj.service.interfaces.LoginService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/23 13:42
 */
@RestController
@RequestMapping("tmall")
public class LoginController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LoginService loginService;

    /**
     * 异步处理2：使用springBoot自带async注解
     */
    @RequestMapping(value = "test1", method = RequestMethod.GET)
    public String test1() {
        loginService.getTest1();
        logger.info("============>" + Thread.currentThread().getName());
        return "异步,正在解析......";
    }
}