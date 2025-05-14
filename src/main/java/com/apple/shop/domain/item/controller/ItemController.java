package com.apple.shop.domain.item.controller;

import com.apple.shop.domain.item.repo.ItemRepository;
import com.apple.shop.domain.item.entiity.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/detail/{id}")
    String detail(@PathVariable Integer id){
        Long id_= Long.valueOf(id);
        Optional<Item> result = itemRepository.findById(id_);
        return "detail.html";
    }

    @PostMapping("/test")
    String test(Model model){
        return model.toString();
    }
}
