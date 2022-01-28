package com.example.demo.controller;

import com.example.demo.config.RedisDBChangeUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisDBChangeUtil redisDBChangeUtil;

    @RequestMapping("/test")
    public String test(){
        String str = "test123";
        return str;
    }

    @RequestMapping("/changeDb")
//    @PostMapping("/changeDb")
    public String changeDb(){

        //默认的插入
        stringRedisTemplate.opsForValue().set("name","db0");

        //插入db1
        redisDBChangeUtil.setDataBase(1);
        stringRedisTemplate.opsForValue().set("name1","db1");

        //插入db2
        redisDBChangeUtil.setDataBase(2);
        stringRedisTemplate.opsForValue().set("name2","db2");

        return  "ok";
    }

}
