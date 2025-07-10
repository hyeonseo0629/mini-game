package com.hs.minigame.mapper.notice;

import com.hs.minigame.vo.TextsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NoticeMapper {

    @Select("select * from texts where TEXT_TYPE = 'NOTICE'")
    public List<TextsVO> selectAll();
}
