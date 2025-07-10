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

    public List<UserScoreVO> selectScoreRanking(){
        return rankingMapper.selectScoreRanking();
    }

    public List<UserStackVO> selectStackRanking() {
        return rankingMapper.selectStackRanking();
    }

    public List<UsersVO> selectMoneyRanking() {
        return rankingMapper.selectMoneyRanking();
    }
}
