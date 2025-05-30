package com.apple.shop.domain.item.controller;

import com.apple.shop.domain.item.service.ItemService;
import com.apple.shop.domain.item.entity.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;


    // 초기 리스트
   @GetMapping("/")
    public String redirectToList() {
        return "redirect:/item/list";
    }

    @GetMapping("/list")
    String list(Model model) {
        List<Item> itemList = itemService.GetItemList();

        model.addAttribute("itemList", itemList);
        return "item/list";
    }


    // 1. 상품 등록
    @GetMapping("/write")
    String write() {
        return "item/write";
    }

    @PostMapping("/add")
    String add(String title, int price, Model model) {
        boolean result = itemService.SavaItem(title, price, model);


        if (!result) {
            return "item/write"; // 실패 시 다시 입력페이지로
        }
        return "redirect:/item/list"; // 성공 시 리스트로

    }

    // 2. 상품 수정
    @GetMapping("/modify/{id}")
    String modify(@PathVariable Long id, Model model) {
        Optional<Item> opt = itemService.FindItem(id);

        if (opt.isPresent()) {   // 존재하면 true
            model.addAttribute("data", opt.get());
        }
        return "item/modify";
    }

    @PostMapping("/updata")
    String updata(Long id, String title, Integer price, Model model) {
        boolean result;

        result = itemService.ChangeItem(id, title, price, model);

        if (!result) {
            return "/modify/" + id; // 실패 시 다시 입력페이지로
        }
        return "redirect:/list"; // 성공 시 리스트로

    }


    // 3. 상품 삭제
    @DeleteMapping("/items/{id}")
    @ResponseBody
    public String delete(@PathVariable Long id) {
        itemService.DeleteItem(id);
        return "success";
    }





    //자세히 보기
    @GetMapping("/detail/{id}")
    String detail(@PathVariable Long id, Model model) {
        Optional<Item> opt = itemService.FindItem(id);

        if (opt.isPresent()) {   // 존재하면 true
            model.addAttribute("data", opt.get());
            return "item/detail";
        } else {
            return "redirect:/member/list";
        }
    }
}


