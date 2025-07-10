package com.hs.minigame.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// only Data needs
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WinningStackVO {
    private String stack_id;
    private String stack_user_no;
    private String stack_game_id;
    private int stack_point;
}
