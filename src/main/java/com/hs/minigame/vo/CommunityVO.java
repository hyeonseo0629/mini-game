package com.hs.minigame.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

// only Data needs
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommunityVO {
        private String text_id;
        private String text_title;
        private String text_user_no;
        private String text_content;
        private Date text_date;
        private String text_type;
}
