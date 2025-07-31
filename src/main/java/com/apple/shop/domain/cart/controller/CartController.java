package com.apple.shop.domain.cart.controller;

import com.apple.shop.view.ViewPath;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/cart")
public class CartController {

    // https://cart.coupang.com/cartView.pang
    @GetMapping
    String cartView(){
        return "cart/cartView";
    }

    @PostMapping
    String addToCart(){
        return "";
    }
}
