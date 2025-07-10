package com.hs.minigame.service.community;

import com.hs.minigame.mapper.community.CommunityMapper;
import com.hs.minigame.vo.CommunityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// model
@Service
public class CommunityService {

    @Autowired
    private CommunityMapper communityMapper;

//    public List<CommunityVO> selectAll() {
//        return communityMapper.selectAll();
//    }

    public List<CommunityVO> getAllReview() {
        List<CommunityVO> community = communityMapper.selectAll();
        System.out.println(community);
        return community;
    }
}
