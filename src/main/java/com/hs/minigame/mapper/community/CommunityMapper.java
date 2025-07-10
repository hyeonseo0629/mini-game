package com.hs.minigame.mapper.community;

import com.hs.minigame.vo.CommunityVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

// mapper -> sql
@Mapper
public interface CommunityMapper {

//    @Select("select * from texts where TEXT_TYPE = 'COMMUNITY'")
//    public List<CommunityVO> selectAll();

    @Select("select * from texts where TEXT_TYPE = 'COMMUNITY'")
    public List<CommunityVO> selectAll();
}
