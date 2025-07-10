package com.hs.minigame.service.ranking;

import com.hs.minigame.mapper.ranking.RankingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankingService {

    @Autowired
    private RankingMapper rankingMapper;

    public Object getAllRanking() {
        return null;
    }
}
