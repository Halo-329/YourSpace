package com.apple.shop.domain.member.service;

import com.apple.shop.domain.member.entity.Member;
import com.apple.shop.domain.member.repo.MemberRepo;
import com.apple.shop.domain.member.validator.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

     private final MemberRepo memberRepo;
    private final MemberValidator memberValidator = new MemberValidator();

    public boolean SavaMember( String loginId  , String loginPw,  String Email,Model model) {

        Member member = new Member();

        if (!memberValidator.validateInput(loginId, loginPw, Email)) {
            model.addAttribute("error", "아이디 혹은 비밀번호는 20자 이하여야 합니다.");
            return false;
        }
        else if(IsExistLoginId(loginId)){
            model.addAttribute("error", "이미 존재하는 ID입니다.");
            return false;
        }
        else {
            member.setLoginId(loginId);
            member.setLoginPw(loginPw);
            member.setEmail(Email);
            memberRepo.save(member);
            return true;
        }
    }

    public boolean IsExistLoginId(String loginId ){
        if(memberRepo.existsByLoginId(loginId)){
            return true;
        }
        return false;

    }





}
