package com.hs.minigame.controller;

import com.hs.minigame.service.shop.ShopService;
import com.hs.minigame.vo.ShopItemsVO;
import com.hs.minigame.vo.UsersVO;
import jakarta.servlet.http.HttpSession;
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
    public String shopC(@RequestParam(defaultValue = "1") int page,HttpSession session, Model model) {
        //select랑 paging기능 병합

        //한페이지에 최대 몇개 보여줄건지
        int limit = 4;

        List<ShopItemsVO> pagedItems = shopService.getShopItems(page, limit);
        int totalCount = shopService.getShopItemCount();
        int totalPage = (int)Math.ceil((double)totalCount / limit);

        //session 관련 로직
        UsersVO user = (UsersVO) session.getAttribute("users");
        if (user != null) {
            model.addAttribute("userMoney", user.getUser_money());
        } else {
            model.addAttribute("userMoney", 0);
        }

        //paging 관련 로직
        model.addAttribute("pagedItems", pagedItems);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", page);

        //select 로직
        model.addAttribute("content", "shop/shop_main.jsp");

        return "main_page";
    }

    @PostMapping("/BuyItem")
    public String buyItemC(@RequestParam("itemId") String itemId , HttpSession session, @RequestParam(defaultValue = "1") int page , Model model) {
        System.out.println("itemID : " + itemId);

        int limit = 4;
        List<ShopItemsVO> pagedItems = shopService.getShopItems(page, limit);
        int totalCount = shopService.getShopItemCount();
        int totalPage = (int)Math.ceil((double)totalCount / limit);

        model.addAttribute("pagedItems", pagedItems);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", page);

        //유저 아이디 조회
        UsersVO user = (UsersVO) session.getAttribute("users");
        System.out.println("user : " + user);
        model.addAttribute("user", user);



        //itemID로 해당 아바타 가격, 이름 조회
        ShopItemsVO itemVO = shopService.getItemById(itemId);
        int itemPrice = itemVO.getItem_price();
        String avatar = itemVO.getItem_avatar_img();
        System.out.println("avatar : " + avatar);
        System.out.println("price : " + itemPrice);

        //금액이 부족하면 잔액부족 예외처리
        if(user.getUser_money() < itemPrice){
            System.out.println("보유 금액 부족");

            model.addAttribute("errorMsg", "보유 금액이 부족합니다.");
            model.addAttribute("content", "shop/shop_main.jsp");
            model.addAttribute("isGamePage", 0);
            return "main_page";
        }

        //userdb에서 가격에 따른 보유금액 변동, 아바타 변경 업데이트
        int updatcheck = shopService.buyitem(user.getUser_id(), itemPrice);

        if (updatcheck > 0) {
            System.out.println("구매 완료");

            // 최신 user_money 조회 후 반영
            int aftermoney = shopService.getUserMoney(user.getUser_id());
            System.out.println("aftermoney : " + aftermoney);

            // 세션 객체도 업데이트
            user.setUser_money(aftermoney);
            session.setAttribute("users", user);
        } else {
            System.out.println("구매 오류");
        }

        int aftermoney = user.getUser_money();
        System.out.println("aftermoney : " + aftermoney);

        model.addAttribute("userMoney", aftermoney);
        model.addAttribute("content", "shop/shop_main.jsp");
        return "main_page";
    }
}