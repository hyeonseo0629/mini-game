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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RankingC {

    @Autowired
    private RankingService rankingService;

    private static final int LIMIT = 10;

    @GetMapping("/RankingC")
    public String getRankingScore(Model model, @RequestParam(defaultValue = "1") int page) {
        return handleScoreRank(model, page);
    }

    @PostMapping("/RankingC")
    public String postRankingScore(Model model, @RequestParam String rankingType,
                                   @RequestParam(defaultValue = "1") int page) {
        switch (rankingType) {
            case "winningStack" :
                return handleStackRank(model, page);
            case "money" :
                return handleMoneyRank(model, page);
            default :
                return handleScoreRank(model, page);
        }
    }

    // 레이팅 점수 처리 메서드
    private String handleScoreRank(Model model, int page) {
        if (page == 1) { // 첫 번째 페이지 -> 전체를 받아와서 Top3와 7개만 처리
            List<UserScoreVO> fullRanking = rankingService.selectScoreRanking();
            model.addAttribute("rankingList", fullRanking);
            model.addAttribute("totalCount", fullRanking.size());
        } else {
            int offset = 10 + (page - 2) * LIMIT; // Top 10 이후의 offset
            // (1) 페이지별 데이터 가져오기
            List<UserScoreVO> pagedRanking = rankingService.selectScoreRankingByPage(offset, LIMIT);
            model.addAttribute("rankingList", pagedRanking);
            // (2) 전체 데이터 개수 가져오기 (페이징 버튼 계산용)
            model.addAttribute("totalCount", rankingService.getScoreRankingCount());
        }
        model.addAttribute("rankingType", "레이팅 점수");
        return commonModelSetup(model, page);
    }

    // 연승 횟수 처리 메서드
    private String handleStackRank(Model model, int page) {
        if (page == 1) {
            List<UserStackVO> fullRanking = rankingService.selectStackRanking();
            model.addAttribute("rankingList", fullRanking);
            model.addAttribute("totalCount", fullRanking.size());
        } else {
            int offset = 10 + (page - 2) * LIMIT;
            // (1)
            List<UserStackVO> pagedRanking = rankingService.selectStackRankingByPage(offset, LIMIT);
            model.addAttribute("rankingList", pagedRanking);
            // (2)
            model.addAttribute("totalCount", rankingService.getStackRankingCount());
        }
        model.addAttribute("rankingType", "연승 횟수");
        return commonModelSetup(model, page);
    }

    // 보유 금액 처리 메서드
    public String handleMoneyRank(Model model, int page) {
        if (page == 1) {
            List<UsersVO> fullRanking = rankingService.selectMoneyRanking();
            model.addAttribute("rankingList", fullRanking);
            model.addAttribute("totalCount", fullRanking.size());
        } else {
            int offset = 10 + (page - 2) * LIMIT;
            // (1)
            List<UsersVO> pagedRanking = rankingService.selectMoneyRankingByPage(offset, LIMIT);
            model.addAttribute("rankingList", pagedRanking);
            // (2)
            model.addAttribute("totalCount", rankingService.getMoneyRankingCount());
        }
        model.addAttribute("rankingType", "보유 금액");
        return commonModelSetup(model, page);
    }

    // 공통 마지막 처리 메서드
    public String commonModelSetup(Model model, int page) {
        model.addAttribute("page", page);

        model.addAttribute("content", "ranking/ranking_main.jsp");
        return "main_page";
    }
}
