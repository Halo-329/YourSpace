package com.apple.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    @GetMapping("/list")
    String list(Model model){
       List<Item> result= itemRepository.findAll();
        System.out.println(result);

        model.addAttribute("name" ,"홍길동");
        return "list.html";
    }

    @GetMapping("/write")
    String write(){
        return "write.html";
    }

    @PostMapping("/add")
    String add(@ModelAttribute Item item){
        itemRepository.save(item);
        return "redirect:/write";
    }

    @PostMapping("/test")
    String test(Model model){
        return model.toString();
    }
}
