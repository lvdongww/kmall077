package com.kgc.kmall.user.controller;

import com.kgc.kmall.service.MemberService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author shkstart
 * @create 2020-12-15 17:12
 */
@Controller
public class MemberController {
    @Reference
    MemberService memberService;
    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return memberService.selectAllMember().get(1).getCity();
    }
}
