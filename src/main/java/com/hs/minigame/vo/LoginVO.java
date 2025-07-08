package com.hs.minigame.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// only Data needs
@NoArgsConstructor
@AllArgsConstructor

@Data
public class LoginVO {
    private String user_id;
    private String user_pw;
    private String user_name;
}
