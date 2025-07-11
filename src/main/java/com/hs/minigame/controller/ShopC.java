package com.hs.minigame.controller;

import com.hs.minigame.service.shop.ShopService;
import com.hs.minigame.vo.ShopItemsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ShopC {

    @Autowired
    private ShopService shopService;

    @GetMapping("/ShopC")
    public String shopC(@RequestParam(defaultValue = "1") int page, Model model) {
        //select랑 paging기능 병합

        //한페이지에 최대 몇개 보여줄건지
        int limit = 4;

        List<ShopItemsVO> pagedItems = shopService.getShopItems(page, limit);
        int totalCount = shopService.getShopItemCount();
        int totalPage = (int)Math.ceil((double)totalCount / limit);

        //paging 관련 로직
        model.addAttribute("pagedItems", pagedItems);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", page);

        //select 로직
        model.addAttribute("content", "shop/shop_main.jsp");
        model.addAttribute("isGamePage", 0);

        return "main_page";
    }

    @PostMapping("/BuyItem")
    public String buyItemC(@RequestParam("itemId") String itemId ,@RequestParam(defaultValue = "1") int page ,Model model) {
        System.out.println("itemID : " + itemId);

        int limit = 4;
        List<ShopItemsVO> pagedItems = shopService.getShopItems(page, limit);
        int totalCount = shopService.getShopItemCount();
        int totalPage = (int)Math.ceil((double)totalCount / limit);

        model.addAttribute("pagedItems", pagedItems);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", page);


        model.addAttribute("content", "shop/shop_main.jsp");
        model.addAttribute("isGamePage", 0);
        return "main_page";
    }

}
