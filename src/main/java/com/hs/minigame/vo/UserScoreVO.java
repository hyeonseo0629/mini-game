package com.hs.minigame.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserScoreVO {
    private String user_id;
    private String user_nickname;
    private String user_avatar_img;
    private int score_point;
}
