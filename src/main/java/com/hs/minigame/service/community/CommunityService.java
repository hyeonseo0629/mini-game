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
        System.out.println(community);
        return community;
    }

    public List<TextsVO> setNewCommunity() {
        return communityMapper.insertCommunity();
    }

    public List<TextsVO> selectCommunityTexts(int page, int limit) {
        int offset = (page - 1) * limit;
        return communityMapper.selectCommunityTexts(offset, limit);

    }

    public int communityCount() {
        return communityMapper.communityCount();
    }
}
