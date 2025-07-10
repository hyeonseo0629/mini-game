package com.hs.minigame.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

// only Data needs
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BuyingRecordVO {
    private String buying_id;
    private String buying_user_no;
    private String buying_item_id;
    private Date buying_date;
}
