package com.apple.shop.domain.cart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/cart")
public class CartController {

    // https://cart.coupang.com/cartView.pang
    @GetMapping("cartView")
    String cartView(Model model){
        List<Integer> list = new ArrayList<>();

        list.add(0);
        list.add(1);

        model.addAttribute("list", list);
        return "/cart/cartView";
    }

}
