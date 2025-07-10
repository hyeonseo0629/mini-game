package com.hs.minigame.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// only Data needs
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GamesVO {
    private String game_id;
    private String game_name;
    private String game_explain;
}
