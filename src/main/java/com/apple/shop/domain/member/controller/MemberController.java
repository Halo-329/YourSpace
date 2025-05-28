package com.apple.shop.domain.member.controller;

import com.apple.shop.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    // 1. 회원가입
    @GetMapping("/member/signup")
    String signup(){
        return "member/signup";
    }

    @PostMapping("/member/add")
    String add(String usrID, String password, String email, Model model) {
        boolean result= memberService.SavaMember(usrID,password,email, model);

        if (!result) {
            return "member/signup"; // 실패 시 다시 입력페이지로
        }
        return "redirect:/list"; // 성공 시 리스트로
    }
}
