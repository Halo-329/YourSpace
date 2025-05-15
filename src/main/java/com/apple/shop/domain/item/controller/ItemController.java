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
        List<Item> itemList= itemRepository.findAll();

        model.addAttribute("itemList", itemList);
        return "list";
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
    String detail(@PathVariable Integer id,Model model){
        Long id_;
        Optional<Item> opt ;
        Item item=null;


        id_ = Long.valueOf(id);
        opt = itemRepository.findById(id_);

        if (opt.isPresent()){   // 존재하면 true
            System.out.println(opt.get());
             item=opt.get();
             model.addAttribute("title",item.getTitle());
             model.addAttribute("price",item.getPrice());

        }


        return "detail.html";
    }

    @PostMapping("/test")
    String test(Model model){
        return model.toString();
    }
}
