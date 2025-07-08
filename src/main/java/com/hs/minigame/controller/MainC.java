package com.hs.minigame.controller;

import com.hs.minigame.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainC {

    @Autowired
    private SampleService sampleService;

    @GetMapping("/")
    public String index(Model model) {
        // you can add this as attribute on model if you need.
        // sampleService.selectAll();
        model.addAttribute("content", "game/game_menu.jsp");
        return "main_page";
    }

    @GetMapping("/GameC")
    public String gameC(Model model) {
        model.addAttribute("content", "game/game_menu.jsp");
        model.addAttribute("isGamePage", 1);
        return "main_page";
    }

    @GetMapping("/ShopC")
    public String shopC(Model model) {
        model.addAttribute("content", "shop/shop_main.jsp");
        model.addAttribute("isGamePage", 0);
    }
    
    @GetMapping("/RankingC")
    public String rankingC(Model model) {
        model.addAttribute("content", "ranking/ranking_menu.jsp");
        model.addAttribute("isRankingPage", 0);
    }

    @GetMapping("/CommunityC")
    public String communityC(Model model) {
        model.addAttribute("content", "community/community_main.jsp");
        model.addAttribute("isGamePage", 1);
        return "main_page";
    }

    @GetMapping("/SupportC")
    public String supportC(Model model) {
        model.addAttribute("content", "support/support_main.jsp");
        model.addAttribute("isGamePage", 1);
        return "main_page";
    }
}
