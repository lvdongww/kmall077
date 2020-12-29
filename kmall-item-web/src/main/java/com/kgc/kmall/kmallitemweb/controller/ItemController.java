package com.kgc.kmall.kmallitemweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author shkstart
 * @create 2020-12-29 14:02
 */
@Controller
public class ItemController {
    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "index";
    }
}
