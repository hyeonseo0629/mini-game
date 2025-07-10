package com.hs.minigame.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

// only Data needs
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScoreVO {
    private String score_id;
    private String score_user_no;
    private String score_game_id;
    private int score_point;
}
