package com.apple.shop.domain.cart.controller;

import com.apple.shop.view.ViewPath;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {

    @GetMapping("/view")
    public String cartView(){
        return ViewPath.CART_VIEW;
    }
}
