package com.hs.minigame.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

// only Data needs
@NoArgsConstructor
@AllArgsConstructor

@Data
public class UsersVO {
    private int user_no;
    private String user_id;
    private String user_pw;
    private String user_name;
    private String user_nickname;
    private String user_role;
    private int user_money;
    private Date user_created_at;
    private String user_avatar_img;
}