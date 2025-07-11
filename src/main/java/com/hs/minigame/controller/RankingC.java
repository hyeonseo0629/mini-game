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

    @GetMapping("/RankingC")
    public String getRankingScore(Model model, @RequestParam(defaultValue = "1") int page) {
        int limit = 10;

        if (page == 1) { // 첫 번째 페이지 -> 전체를 받아와서 Top3와 7개만 처리
            List<UserScoreVO> fullRanking = rankingService.selectScoreRanking();
            model.addAttribute("rankingList", fullRanking);
            model.addAttribute("totalCount", fullRanking.size());
        } else {
            int offset = 10 + (page - 2) * limit; // Top 10 이후의 offset
            // (1) 페이지별 데이터 가져오기
            List<UserScoreVO> pagedRanking = rankingService.selectScoreRankingByPage(offset, limit);
            System.out.println(pagedRanking);
            model.addAttribute("rankingList", pagedRanking);
            // (2) 전체 데이터 개수 가져오기 (페이징 버튼 계산용)
            model.addAttribute("totalCount", rankingService.getScoreRankingCount());
        }
        model.addAttribute("rankingType", "레이팅 점수");
        model.addAttribute("page", page);

        model.addAttribute("content", "ranking/ranking_main.jsp");
        return "main_page";
    }

    @PostMapping("/RankingC")
    public String postRankingScore(Model model, @RequestParam String rankingType,
                                   @RequestParam(defaultValue = "1") int page) {
        int limit = 10;

        if (rankingType.equals("winningStack")) {
            if (page == 1) {
                List<UserStackVO> fullRanking = rankingService.selectStackRanking();
                model.addAttribute("rankingList", fullRanking);
                model.addAttribute("totalCount", fullRanking.size());
            } else {
                int offset = 10 + (page - 2) * limit;
                // (1)
                List<UserStackVO> pagedRanking = rankingService.selectStackRankingByPage(offset, limit);
                model.addAttribute("rankingList", pagedRanking);
                // (2)
                model.addAttribute("totalCount", rankingService.getStackRankingCount());
            }
            model.addAttribute("rankingType", "연승 횟수");
        } else if (rankingType.equals("money")) {
            if (page == 1) {
                List<UsersVO> fullRanking = rankingService.selectMoneyRanking();
                model.addAttribute("rankingList", fullRanking);
                model.addAttribute("totalCount", fullRanking.size());
            } else {
                int offset = 10 + (page - 2) * limit;
                // (1)
                List<UsersVO> pagedRanking = rankingService.selectMoneyRankingByPage(offset, limit);
                model.addAttribute("rankingList", pagedRanking);
                // (2)
                model.addAttribute("totalCount", rankingService.getMoneyRankingCount());
            }
            model.addAttribute("rankingType", "보유 금액");
        } else {
            if (page == 1) { // 첫 번째 페이지 -> 전체를 받아와서 Top3와 7개만 처리
                List<UserScoreVO> fullRanking = rankingService.selectScoreRanking();
                model.addAttribute("rankingList", fullRanking);
                model.addAttribute("totalCount", fullRanking.size());
            } else {
                int offset = 10 + (page - 2) * limit; // Top 10 이후의 offset
                // (1) 페이지별 데이터 가져오기
                List<UserScoreVO> pagedRanking = rankingService.selectScoreRankingByPage(offset, limit);
                model.addAttribute("rankingList", pagedRanking);
                // (2) 전체 데이터 개수 가져오기 (페이징 버튼 계산용)
                model.addAttribute("totalCount", rankingService.getScoreRankingCount());
            }
            model.addAttribute("rankingType", "레이팅 점수");
        }
        model.addAttribute("page", page);
        model.addAttribute("content", "ranking/ranking_main.jsp");
        return "main_page";
    }

}
