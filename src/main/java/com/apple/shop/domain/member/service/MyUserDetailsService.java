package com.apple.shop.domain.member.service;

import com.apple.shop.domain.member.repo.MemberRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final MemberRepo memberRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var opt = memberRepo.findFirstByLoginId(username);
        List<GrantedAuthority> auth= new ArrayList<>();

        if(opt.isEmpty()){
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username);
        }
        auth.add(new SimpleGrantedAuthority("일반유저"));

        return new User(opt.get().getLoginId(), opt.get().getLoginPw(), auth);

    }
}
