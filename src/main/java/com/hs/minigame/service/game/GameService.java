package com.hs.minigame.service.game;

import com.hs.minigame.mapper.SampleMapper;
import com.hs.minigame.mapper.game.GameMapper;
import com.hs.minigame.vo.SampleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// model
@Service
public class GameService {

    @Autowired
    private GameMapper gameMapper;

    public Map<String, Integer> processGameResult(int user_no, String result) {
        Map<String, Integer> resultMap = new HashMap<>();
        int rewardMoney = 0;

        if (result.equals("win")) {
            // score 테이블에 user_no의 레이팅 값 존재 시 UPDATE(+500), 미존재 시 INSERT
            if (gameMapper.selectScoreCount(user_no) != 0) gameMapper.updateWinScore(user_no);
            else gameMapper.insertWinScore(user_no);
            System.out.println("score 업데이트++ 완료!");

            // winning_stack 테이블에 user_no의 연승 값 존재 시 UPDATE(+1), 미존재 시 INSERT
            // + 연승 횟수를 Attribute로 추가
            if (gameMapper.selectStackCount(user_no) != 0) {
                gameMapper.updateWinStack(user_no);
                resultMap.put("stackPoint", gameMapper.selectStackPoint(user_no));
            } else {
                gameMapper.insertWinStack(user_no);
                resultMap.put("stackPoint", 1);
            }
            System.out.println("stack 업데이트++ 완료!");

            rewardMoney = 10000;
        } else if (result.equals("lose")) {
            // score, winning_stack 테이블에 user_no의 값 존재 시 UPDATE(-300, 0)
            resultMap.put("stackPoint", gameMapper.selectStackPoint(user_no));
            if (gameMapper.selectScoreCount(user_no) != 0) gameMapper.updateLoseScore(user_no);
            if (gameMapper.selectStackCount(user_no) != 0) gameMapper.updateLoseStack(user_no);
            System.out.println("score 업데이트-- 완료!");
            System.out.println("stack 업데이트-- 완료!");

            rewardMoney = -1000;
        }
        gameMapper.updateMoney(user_no, rewardMoney);
        System.out.println("money 업데이트 완료!");

        resultMap.put("rewardMoney", rewardMoney);
        return resultMap;
    }

    public int getUserMoney(int userId) {
        return gameMapper.getUserMoney(userId);
    }
}
