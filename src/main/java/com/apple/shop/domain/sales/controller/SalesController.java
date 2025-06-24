package com.apple.shop.domain.sales.controller;

import com.apple.shop.domain.item.entity.Item;
import com.apple.shop.domain.item.service.ItemService;
import com.apple.shop.domain.sales.service.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sales")
public class SalesController
{
    private final ItemService itemService;
    private final SalesService salesService;



    // 1. 주문 및 결제
    // 1.1 주문하기
    @PostMapping("/order")
    String order(Model model, @RequestParam Long itemId ){
        Optional<Item> opt = itemService.FindItem(itemId);

        if(opt.isPresent()){
            model.addAttribute("item", opt.get());
        }

        return "/sales/order";    // html return
    }

    // 1.2 결제하기
    @PostMapping("/pay")
    String pay(Model model, @RequestParam Long itemId, @RequestParam int count, Authentication auth){
        Optional<Item> opt = itemService.FindItem(itemId);

        if(opt.isPresent()){
            salesService.saveItemPayRecode(itemId,count, auth);
        }

        return "redirect:/item/list";    // html return
    }




    // 2. 주문 내역 조회
    @GetMapping("/list")
    String orderList(Model model){

        model.addAttribute("orders", salesService.getAllOrderDetailList());
        return "sales/list";
    }

}
