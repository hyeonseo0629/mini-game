package com.hs.minigame.controller;

import com.hs.minigame.service.ranking.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RankingC {

    @Autowired
    private RankingService rankingService;

    @GetMapping("/RankingC")
    public String rankingC(Model model) {
        model.addAttribute("content", "ranking/ranking_main.jsp");
        model.addAttribute("isGamePage", 0);
        return "main_page";
    }
}
