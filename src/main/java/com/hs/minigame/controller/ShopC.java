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


    //리미트 상수화
    private static final int PAGE_LIMIT = 4;

    @Autowired
    private ShopService shopService;

    @GetMapping("/ShopC")
    public String shopC(@RequestParam(defaultValue = "1") int page, HttpSession session, Model model) {
        setPagingData(model, page);
        setUserMoneyToModel(session, model);

        model.addAttribute("content", "shop/shop_main.jsp");
        return "main_page";
    }

    @PostMapping("/BuyItem")
    public String buyItemC(@RequestParam("itemId") String itemId,
                           HttpSession session,
                           @RequestParam(defaultValue = "1") int page,
                           Model model) {

        setPagingData(model, page);
        UsersVO user = setUserMoneyToModel(session, model);

        if (user == null) {
            model.addAttribute("errorMsg", "로그인이 필요합니다.");
            model.addAttribute("content", "shop/shop_main.jsp");
            return "main_page";
        }

        // 아이템 정보 조회
        ShopItemsVO itemVO = shopService.getItemById(itemId);
        int itemPrice = itemVO.getItem_price();
        String avatar = itemVO.getItem_avatar_img();

        System.out.println("itemID : " + itemId);
        System.out.println("avatar : " + avatar);
        System.out.println("price : " + itemPrice);

        // 잔액 부족 처리(이중 췤)
        if (user.getUser_money() < itemPrice) {
            System.out.println("보유 금액 부족");

            model.addAttribute("errorMsg", "보유 금액이 부족합니다.");
            model.addAttribute("content", "shop/shop_main.jsp");
            model.addAttribute("isGamePage", 0);
            return "main_page";
        }

        // 구매 처리
        int updateCheck = shopService.buyitem(user.getUser_id(), itemPrice);
        if (updateCheck > 0) {
            System.out.println("구매 완료");

            // 세션 반영
            int afterMoney = shopService.getUserMoney(user.getUser_id());
            user.setUser_money(afterMoney);
            session.setAttribute("users", user);
            model.addAttribute("userMoney", afterMoney);
        } else {
            System.out.println("구매 오류");
        }

        model.addAttribute("content", "shop/shop_main.jsp");
        return "main_page";
    }

    // 1 공통 처리 메서드(페이징)
    private void setPagingData(Model model, int page) {
        List<ShopItemsVO> pagedItems = shopService.getShopItems(page, PAGE_LIMIT);
        int totalCount = shopService.getShopItemCount();
        int totalPage = (int) Math.ceil((double) totalCount / PAGE_LIMIT);

        model.addAttribute("pagedItems", pagedItems);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", page);
    }

    // 2 공통 처리 메서드(로그인 전에 상점 페이지에 뜰 보유 가격)
    private UsersVO setUserMoneyToModel(HttpSession session, Model model) {
        UsersVO user = (UsersVO) session.getAttribute("users");
        int money = (user != null) ? user.getUser_money() : 0;
        model.addAttribute("userMoney", money);
        return user;
    }
}
