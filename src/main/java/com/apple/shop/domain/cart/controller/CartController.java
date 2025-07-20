package com.apple.shop.domain.cart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/cart")
public class CartController {

    // https://cart.coupang.com/cartView.pang
    @GetMapping("cartView")
    String cartView(){

        return "/cart/cartView";
    }

}
