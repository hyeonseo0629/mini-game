package com.hs.minigame.service.ranking;

import com.hs.minigame.mapper.ranking.RankingMapper;
import com.hs.minigame.vo.UserScoreVO;
import com.hs.minigame.vo.UserStackVO;
import com.hs.minigame.vo.UsersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingService {

    @Autowired
    private RankingMapper rankingMapper;

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
}
