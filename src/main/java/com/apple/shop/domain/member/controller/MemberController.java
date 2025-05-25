package com.apple.shop.domain.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @GetMapping("/member/signup")
    String signup(){
        return "member/signup";
    }

//    @PostMapping("/member/")
}
