package com.hs.minigame.controller;

import com.hs.minigame.service.shop.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShopC {

    @Autowired
    private ShopService shopService;

    @GetMapping("/ShopC")
    public String shopC(Model model) {
        model.addAttribute("itemsInfo", shopService.selectAll());
        model.addAttribute("content", "shop/shop_main.jsp");
        model.addAttribute("isGamePage", 0);
        return "main_page";
    }

    @PostMapping("/BuyItem")
    public String buyItemC(@RequestParam("itemId") String itemId , Model model) {
        System.out.println("itemID : " + itemId);
        model.addAttribute("itemsInfo", shopService.selectAll());
        model.addAttribute("content", "shop/shop_main.jsp");
        model.addAttribute("isGamePage", 0);
        return "main_page";
    }
}
