package com.hs.minigame.service.ranking;

import com.hs.minigame.mapper.ranking.RankingMapper;
import com.hs.minigame.vo.UserScoreVO;
import com.hs.minigame.vo.UserStackVO;
import com.hs.minigame.vo.UsersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class RankingService {

    @Autowired
    private RankingMapper rankingMapper;

    private static final int LIMIT = 10;

    // all select (사용 X)
    public List<UserScoreVO> selectScoreRanking(){
        return rankingMapper.selectScoreRanking();
    }

    public List<UserStackVO> selectStackRanking() {
        return rankingMapper.selectStackRanking();
    }

    public List<UsersVO> selectMoneyRanking() {
        return rankingMapper.selectMoneyRanking();
    }

    // (1) 페이지별 데이터 가져오기
    public List<UserScoreVO> selectScoreRankingByPage(int offset, int limit) {
        return rankingMapper.selectScoreRankingByPage(offset, limit);
    }

    public List<UserStackVO> selectStackRankingByPage(int offset, int limit) {
        return rankingMapper.selectStackRankingByPage(offset, limit);
    }

    public List<UsersVO> selectMoneyRankingByPage(int offset, int limit) {
        return rankingMapper.selectMoneyRankingByPage(offset, limit);
    }

    // (2) 전체 데이터 개수 가져오기
    public int getScoreRankingCount() {
        return rankingMapper.getScoreRankingCount();
    }

    public int getStackRankingCount() {
        return rankingMapper.getStackRankingCount();
    }

    public int getMoneyRankingCount() {
        return rankingMapper.getMoneyRankingCount();
    }

    // 레이팅 점수 처리 메서드
    public String handleScoreRank(Model model, int page) {
        if (page == 1) { // 첫 번째 페이지 -> 전체를 받아와서 Top3와 7개만 처리
            List<UserScoreVO> fullRanking = selectScoreRanking();
            model.addAttribute("rankingList", fullRanking);
            model.addAttribute("totalCount", fullRanking.size());
        } else {
            int offset = 10 + (page - 2) * LIMIT; // Top 10 이후의 offset
            // (1) 페이지별 데이터 가져오기
            List<UserScoreVO> pagedRanking = selectScoreRankingByPage(offset, LIMIT);
            model.addAttribute("rankingList", pagedRanking);
            // (2) 전체 데이터 개수 가져오기 (페이징 버튼 계산용)
            model.addAttribute("totalCount", getScoreRankingCount());
        }
        model.addAttribute("rankingType", "레이팅 점수");
        return commonModelSetup(model, page);
    }

    // 연승 횟수 처리 메서드
    public String handleStackRank(Model model, int page) {
        if (page == 1) {
            List<UserStackVO> fullRanking = selectStackRanking();
            model.addAttribute("rankingList", fullRanking);
            model.addAttribute("totalCount", fullRanking.size());
        } else {
            int offset = 10 + (page - 2) * LIMIT;
            // (1)
            List<UserStackVO> pagedRanking = selectStackRankingByPage(offset, LIMIT);
            model.addAttribute("rankingList", pagedRanking);
            // (2)
            model.addAttribute("totalCount", getStackRankingCount());
        }
        model.addAttribute("rankingType", "연승 횟수");
        return commonModelSetup(model, page);
    }

    // 보유 금액 처리 메서드
    public String handleMoneyRank(Model model, int page) {
        if (page == 1) {
            List<UsersVO> fullRanking = selectMoneyRanking();
            model.addAttribute("rankingList", fullRanking);
            model.addAttribute("totalCount", fullRanking.size());
        } else {
            int offset = 10 + (page - 2) * LIMIT;
            // (1)
            List<UsersVO> pagedRanking = selectMoneyRankingByPage(offset, LIMIT);
            model.addAttribute("rankingList", pagedRanking);
            // (2)
            model.addAttribute("totalCount", getMoneyRankingCount());
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
