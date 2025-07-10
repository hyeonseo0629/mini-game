package com.hs.minigame.service.notice;

import com.hs.minigame.mapper.notice.NoticeMapper;
import com.hs.minigame.mapper.ranking.RankingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    public Object getAllNotice() {
        return null;
    }
}
