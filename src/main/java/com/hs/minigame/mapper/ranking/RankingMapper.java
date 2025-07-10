package com.hs.minigame.mapper.ranking;

import com.hs.minigame.vo.UserScoreVO;
import com.hs.minigame.vo.UserStackVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RankingMapper {

    @Select("SELECT user_id, user_nickname, user_avatar_img, score_point " +
            "FROM users, score " +
            "WHERE user_no = score_user_no AND score_game_id = 1 " +
            "ORDER BY score_point DESC")
    public List<UserScoreVO> selectScoreRanking();


    @Select("SELECT user_id, user_nickname, user_avatar_img, stack_point " +
            "FROM users, winning_stack " +
            "WHERE user_no = stack_user_no AND stack_game_id = 1 " +
            "ORDER BY stack_point DESC")
    public List<UserStackVO> selectStackRanking();
}
