package com.hs.minigame.service.support;

import com.hs.minigame.mapper.support.SupportMapper;
import com.hs.minigame.vo.SupportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// model
@Service
public class SupportService {

    @Autowired
    private SupportMapper supportMapper;

//    public List<CommunityVO> selectAll() {
//        return communityMapper.selectAll();
//    }

    public List<SupportVO> getAllSupport() {
        List<SupportVO> support = supportMapper.selectAllsupport();
        System.out.println(support);
        return support;
    }
}
