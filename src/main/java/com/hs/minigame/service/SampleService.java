package com.hs.minigame.service;

import com.hs.minigame.vo.SampleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// model
@Service
public class SampleService {

    @Autowired
    private SampleService sampleService;

    public List<SampleVO> selectAll() {
        return sampleService.selectAll();
    }

}
