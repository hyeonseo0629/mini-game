package com.hs.minigame.mapper.support;

import com.hs.minigame.vo.CommunityVO;
import com.hs.minigame.vo.SupportVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

// mapper -> sql
@Mapper
public interface SupportMapper {

//    @Select("select * from texts where TEXT_TYPE = 'COMMUNITY'")
//    public List<CommunityVO> selectAll();

    @Select("select * from texts where TEXT_TYPE = 'QUESTION'")
    public  List<SupportVO> selectAllsupport();
}
