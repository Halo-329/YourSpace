package com.apple.shop.domain.member.controller;

import com.apple.shop.domain.member.entity.Member;
import com.apple.shop.domain.member.repo.MemberRepo;
import com.apple.shop.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.text.html.Option;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepo memberRepo;

    // 1. 회원가입
    @GetMapping("/signup")
    String signup(){
        return "member/signup";
    }

    @PostMapping("/add")
    String add(String usrID, String password, String email, Model model) {
        boolean result= memberService.SavaMember(usrID,password,email, model);

        if (!result) {
            return "member/signup"; // 실패 시 다시 입력페이지로
        }
        return "redirect:/item/list"; // 성공 시 리스트로
    }

    // 2. 로그인
    @GetMapping("/login")
    String login(String username , String password){
        return "member/login";
    }


    // 3. 마이페이지
    @GetMapping("/my-page")
    String me(Authentication auth,Model model){
        String id = auth.getName();
        Optional<Member> opt= memberRepo.findFirstByLoginId(id);

        if (opt.isPresent()){
            model.addAttribute("member", opt.get());
        }

//        return "member/mypage";
        return "member/my-page";
    }

}

