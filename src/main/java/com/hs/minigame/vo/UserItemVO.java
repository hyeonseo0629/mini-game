package com.hs.minigame.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

// only Data needs
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserItemVO {
    private String useritem_id;
    private String useritem_user_no;
    private String useritem_item_id;
}
