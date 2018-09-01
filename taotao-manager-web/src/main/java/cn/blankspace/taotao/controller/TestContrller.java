package cn.blankspace.taotao.controller;

import cn.blankspace.taotao.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestContrller {
    @Autowired
     private TestService testService;


    @RequestMapping("/test/queryNow")
    @ResponseBody
    public String queryNow(){
        System.out.println("laile");
        return testService.queryNow();
    }
}
