package com.hs.minigame.controller;

import com.hs.minigame.service.ranking.RankingService;
import com.hs.minigame.vo.UserScoreVO;
import com.hs.minigame.vo.UserStackVO;
import com.hs.minigame.vo.UsersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/rank")
@Controller
public class RankingC {

    @Autowired
    private RankingService rankingService;

    @GetMapping("")
    public String getRankingScore(Model model, @RequestParam(defaultValue = "1") int page) {
        return rankingService.handleScoreRank(model, page);
    }

    @PostMapping("")
    public String postRankingScore(Model model, @RequestParam String rankingType,
                                   @RequestParam(defaultValue = "1") int page) {
        switch (rankingType) {
            case "winningStack" :
                return rankingService.handleStackRank(model, page);
            case "money" :
                return rankingService.handleMoneyRank(model, page);
            default :
                return rankingService.handleScoreRank(model, page);
        }
    }
}
