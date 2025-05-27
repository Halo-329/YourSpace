package com.apple.shop.domain.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    // 1. 회원가입
    @GetMapping("/member/signup")
    String signup(){
        return "member/signup";
    }

//    @PostMapping("/member/")
}
