package com.hs.minigame.service.notice;

import com.hs.minigame.mapper.notice.NoticeMapper;
import com.hs.minigame.mapper.ranking.RankingMapper;
import com.hs.minigame.vo.TextsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    public List<TextsVO> getAllNotice() {
        List<TextsVO> notice = noticeMapper.selectAll();
        return notice;
    }
}
