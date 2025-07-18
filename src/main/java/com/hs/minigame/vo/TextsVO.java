package com.hs.minigame.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

// only Data needs
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TextsVO {
        private int text_id;
        private String text_title;
        private int text_user_no;
        private String user_nickname;
        private String text_content;
        private Date text_write_date;
        private String text_type;
        private String text_kr;
}
