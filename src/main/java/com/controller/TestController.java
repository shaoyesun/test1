package com.controller;

import com.log.Log;
import com.remote.RemoteDemo;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sunjf on 2016/6/26.
 */
@Controller
public class TestController {

    @Log(desc = "log测试")
    @RequestMapping(value = "test1")
    public String test1(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "sun");
        jsonObject.put("password", "123");
        String result = RemoteDemo.remoteJsonRequest("http://localhost:8080/test2?username=sun&password=123", 5000, jsonObject);
        System.out.println("这是返回结果：" + result);
        return "success";
    }

}
