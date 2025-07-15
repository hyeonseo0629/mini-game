package com.hs.minigame.mapper.game;

import com.hs.minigame.vo.SampleVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

// mapper -> sql
@Mapper
public interface GameMapper {

    // score, stack 이력에 userNo 존재하는지 확인
    @Select("SELECT COUNT(*) " +
            "FROM users, score " +
            "WHERE user_no = score_user_no AND score_game_id = 1 AND " +
            "score_user_no = #{userNo}")
    int selectScoreCount(int userNo);

    @Select("SELECT COUNT(*) " +
            "FROM users, winning_stack " +
            "WHERE user_no = stack_user_no AND stack_game_id = 1 AND " +
            "stack_user_no = #{userNo}")
    int selectStackCount(int userNo);

    // 승리 시, score 갱신 또는 삽입
    @Update("UPDATE score " +
            "SET score_point = score_point + 500 " +
            "WHERE score_user_no = #{userNo}")
    void updateWinScore(int userNo);

    @Insert("INSERT INTO(score_user_no, score_game_id, score_point) " +
            "VALUES(#{userNo}, 1, 500)")
    void insertWinScore(int userNo);

    // 승리 시, stack 갱신 또는 삽입
    @Update("UPDATE winning_stack " +
            "SET stack_point = stack_point + 1 " +
            "WHERE stack_user_no = #{userNo}")
    void updateWinStack(int userNo);

    @Select("SELECT stack_point " +
            "FROM winning_stack " +
            "WHERE stack_user_no = #{userNo}")
    int selectStackPoint(int userNo);

    @Insert("INSERT INTO(stack_user_no, stack_game_id, stack_point) " +
            "VALUES(#{userNo}, 1, 1)")
    void insertWinStack(int userNo);

    // 패배 시, score, stack 갱신
    @Update("UPDATE score " +
            "SET score_point = score_point - 300 " +
            "WHERE score_user_no = #{userNo}")
    void updateLoseScore(int userNo);

    @Update("UPDATE winning_stack " +
            "SET stack_point = 0 " +
            "WHERE stack_user_no = #{userNo}")
    void updateLoseStack(int userNo);

    // money 갱신
    @Update("UPDATE users " +
            "SET user_money = user_money + #{rewardMoney} " +
            "WHERE user_no = #{userNo}")
    void updateMoney(int userNo, int rewardMoney);

    // 오른쪽 area의 money 갱신
    @Select("SELECT user_money " +
            "FROM users " +
            "WHERE user_no = #{userId}")
    int getUserMoney(int userId);
}
