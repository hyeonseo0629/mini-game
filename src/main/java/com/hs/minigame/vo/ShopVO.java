package com.hs.minigame.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// only Data needs
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShopVO {
    private String item_id;
    private String item_name;
    private int item_price;
    private String item_avatar_img;
}
