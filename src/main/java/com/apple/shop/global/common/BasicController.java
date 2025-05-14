package com.apple.shop.global.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller //이거 붙이면 서버 기능 제작 가능.
public class BasicController  {
    @GetMapping("/")
    @ResponseBody
    String func(){
        return "안녕하세요";
    }

    @GetMapping("/no")
    String no(){
        return "index.html";
    }

    @GetMapping("/date")
    @ResponseBody
    String date(){
        LocalDateTime now = LocalDateTime.now();
        String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));
        return formatedNow;
    }
}
