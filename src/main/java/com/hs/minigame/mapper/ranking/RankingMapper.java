package com.hs.minigame.mapper.ranking;

import com.hs.minigame.vo.UserScoreVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RankingMapper {

    @Select("SELECT user_id, user_nickname, user_avatar_img, score_point " +
            "FROM users, score " +
            "WHERE user_no = score_user_no AND score_game_id = 1" +
            "ORDER BY score_point DESC")
    public List<UserScoreVO> selectScoreRanking();


}
