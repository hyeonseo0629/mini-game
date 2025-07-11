package com.hs.minigame.mapper.community;

import com.hs.minigame.vo.TextsVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

// mapper -> sql
@Mapper
public interface CommunityMapper {

//    @Select("select * from texts where TEXT_TYPE = 'COMMUNITY'")
//    public List<TextsVO> selectAll();

    @Select("select * from texts where TEXT_TYPE = 'COMMUNITY'")
    public List<TextsVO> selectAll();

    @Insert("insert into texts(text_title, text_content, text_user_no, text_type) values (#{text_title}, #{text_content}, #{users.user_id}, 'COMMUNITY')")
    public List<TextsVO> insertCommunity();

    @Select("SELECT count(*) FROM texts where TEXT_TYPE = 'COMMUNITY'")
    public int communityCount();

    @Select("select * from texts"+" order by text_write_date desc "+
    "offset #{offset} rows fetch next #{limit} rows only")
    List<TextsVO> selectCommunityTexts(@Param("offset") int offset, @Param("limit") int limit);
}
