package com.apple.shop.domain.item.validator;

import org.springframework.ui.Model;

public class ItemValidator {
    public boolean validateInput(String title, Integer price, Model model) {
        boolean valid = true;

        if (title.length() >= 20) {
            valid = false;
            return valid;
        }

        if (price < 0) {
            valid = false;
            return valid;
        }

        return valid;
    }
}
