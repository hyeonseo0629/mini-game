package com.hs.minigame.mapper.ranking;

import com.hs.minigame.vo.UserScoreVO;
import com.hs.minigame.vo.UserStackVO;
import com.hs.minigame.vo.UsersVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RankingMapper {

    // all select (사용 X)
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

    @Select("SELECT user_id, user_nickname, user_avatar_img, user_money " +
            "FROM users " +
            "ORDER BY user_money DESC")
    public List<UsersVO> selectMoneyRanking();

    // (1) 페이지별 데이터 가져오기
    @Select("SELECT user_id, user_nickname, user_avatar_img, score_point " +
            "FROM users, score " +
            "WHERE user_no = score_user_no AND score_game_id = 1 " +
            "ORDER BY score_point DESC " +
            "OFFSET #{offset} ROWS FETCH NEXT #{limit} ROWS ONLY")
    public List<UserScoreVO> selectScoreRankingByPage(int offset, int limit);

    @Select("SELECT user_id, user_nickname, user_avatar_img, stack_point " +
            "FROM users, winning_stack " +
            "WHERE user_no = stack_user_no AND stack_game_id = 1 " +
            "ORDER BY stack_point DESC " +
            "OFFSET #{offset} ROWS FETCH NEXT #{limit} ROWS ONLY")
    public List<UserStackVO> selectStackRankingByPage(int offset, int limit);

    @Select("SELECT user_id, user_nickname, user_avatar_img, user_money " +
            "FROM users " +
            "ORDER BY user_money DESC " +
            "OFFSET #{offset} ROWS FETCH NEXT #{limit} ROWS ONLY")
    public List<UsersVO> selectMoneyRankingByPage(int offset, int limit);

    // (2) 전체 데이터 개수 가져오기
    @Select("SELECT COUNT(*) " +
            "FROM users, score " +
            "WHERE user_no = score_user_no AND score_game_id = 1" )
    public int getScoreRankingCount();

    @Select("SELECT COUNT(*) " +
            "FROM users, winning_stack " +
            "WHERE user_no = stack_user_no AND stack_game_id = 1" )
    public int getStackRankingCount();

    @Select("SELECT COUNT(*) " +
            "FROM users" )
    public int getMoneyRankingCount();
}
