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
        if (rankingType.equals("winningStack")) {
            model.addAttribute("rankingList", rankingService.selectStackRanking());
            model.addAttribute("rankingType", "연승 횟수");
        } else if (rankingType.equals("money")) {
//            model.addAttribute("rankingList", rankingService.selectMoneyRanking());
            model.addAttribute("rankingType", "보유 금액");
        }

        model.addAttribute("content", "ranking/ranking_main.jsp");
        model.addAttribute("isGamePage", 0);
        return "main_page";
    }

}
