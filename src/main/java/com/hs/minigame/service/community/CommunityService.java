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
}
