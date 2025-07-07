package com.hs.minigame.mapper;

import com.hs.minigame.vo.SampleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

// mapper -> sql
@Mapper
public interface SampleMapper {

    @Select("select * from test")
    public List<SampleVO> selectAll();
}
