package com.apple.shop.domain.sales.controller;

import com.apple.shop.domain.item.entity.Item;
import com.apple.shop.domain.item.service.ItemService;
import com.apple.shop.domain.sales.service.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @GetMapping("/order")
    public String orderPage(@RequestParam Long itemId, Model model, @ModelAttribute("error") String error) {
        itemService.FindItem(itemId).ifPresent(item -> model.addAttribute("item", item));
        model.addAttribute("error", error); // FlashAttribute로 넘긴 에러 메시지 표시
        return "/sales/order";
    }

    // 1.2 결제하기
    @PostMapping("/pay")
    String pay(Model model, @RequestParam Long itemId, @RequestParam int count, Authentication auth, RedirectAttributes ra){
        Optional<Item> opt = itemService.FindItem(itemId);

        if(opt.isPresent()){
            boolean result = salesService.isStockAvailable(itemId, count);
            if(!result){
                ra.addFlashAttribute("error", "재고가 부족합니다.");
                ra.addAttribute("itemId", itemId); // 👈 redirect 시 쿼리로 전달
                return "redirect:/sales/order";
            }
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
