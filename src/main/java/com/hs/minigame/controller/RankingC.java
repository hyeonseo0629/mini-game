package com.hs.minigame.controller;

import com.hs.minigame.service.ranking.RankingService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RankingC {

    @Autowired
    private RankingService rankingService;

    @GetMapping("/RankingC")
    public String getRankingScore(Model model) {
        model.addAttribute("rankingList", rankingService.selectScoreRanking());
        model.addAttribute("rankingType", "레이팅 점수");
        model.addAttribute("content", "ranking/ranking_main.jsp");
        model.addAttribute("isGamePage", 0);
        return "main_page";
    }

    @PostMapping("/RankingC")
    public String postRankingScore(Model model, @RequestParam String rankingType) {
        System.out.println(rankingType);

        model.addAttribute("content", "ranking/ranking_main.jsp");
        model.addAttribute("isGamePage", 0);
        return "main_page";
    }

}
