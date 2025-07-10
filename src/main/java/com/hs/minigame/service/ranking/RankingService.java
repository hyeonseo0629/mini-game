package com.hs.minigame.service.ranking;

import com.hs.minigame.mapper.ranking.RankingMapper;
import com.hs.minigame.vo.UserScoreVO;
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
}
