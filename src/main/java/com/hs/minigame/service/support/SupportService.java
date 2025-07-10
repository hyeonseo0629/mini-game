package com.hs.minigame.service.support;

import com.hs.minigame.mapper.support.SupportMapper;
import com.hs.minigame.vo.TextsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// model
@Service
public class SupportService {

    @Autowired
    private SupportMapper supportMapper;

//    public List<TextsVO> selectAll() {
//        return communityMapper.selectAll();
//    }

    public List<TextsVO> getAllSupport() {
        List<TextsVO> support = supportMapper.selectAllsupport();
        System.out.println(support);
        return support;
    }

    public List<TextsVO> setNewSupport() {
        List<TextsVO> insertSupport = supportMapper.insertSupport();
        return insertSupport;
    }
}
