package com.hs.minigame.service.community;

import com.hs.minigame.mapper.community.CommunityMapper;
import com.hs.minigame.vo.TextsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// model
@Service
public class CommunityService {

    @Autowired
    private CommunityMapper communityMapper;

//    public List<TextsVO> selectAll() {
//        return communityMapper.selectAll();
//    }

    public List<TextsVO> getAllReview() {
        List<TextsVO> community = communityMapper.selectAll();
        return community;
    }

    public int insertCommunityText(String text_title, String text_content, int user_no) {
        return communityMapper.insertCommunityText(text_title, text_content, user_no);
    }

//    public void insert(TextsVO textsVO) {
//        communityMapper.insertCommunity(textsVO);
//    }

    public List<TextsVO> selectCommunityTexts(int page, int limit) {
        int offset = (page - 1) * limit;
        return communityMapper.selectCommunityTexts(offset, limit);

    }

    public int communityCount() {
        return communityMapper.communityCount();
    }

    public TextsVO selectCommunityText(int textId) {
        return communityMapper.selectCommunityText(textId);
    }

    public TextsVO getTextById(int text_id) {
        return communityMapper.getTextByID(text_id);
    }

    public void updateCommunityText(String title, String content, String text_id) {
        communityMapper.updateCommunityText(title, content, text_id);
    }

    public void deleteCommunityText(int textId) {
        communityMapper.deleteCommunityText(textId);
    }
}
