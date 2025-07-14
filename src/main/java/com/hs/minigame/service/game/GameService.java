package com.hs.minigame.service.game;

import com.hs.minigame.mapper.SampleMapper;
import com.hs.minigame.vo.SampleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// model
@Service
public class GameService {

    @Autowired
    private SampleMapper sampleMapper;

    public List<SampleVO> selectAll() {
        return sampleMapper.selectAll();
    }

}
