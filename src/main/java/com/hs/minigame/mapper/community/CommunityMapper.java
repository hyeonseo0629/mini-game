package com.hs.minigame.mapper.community;

import com.hs.minigame.vo.TextsVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

// mapper -> sql
@Mapper
public interface CommunityMapper {

//    @Select("select * from texts where TEXT_TYPE = 'COMMUNITY'")
//    public List<TextsVO> selectAll();

    @Select("select * from texts where TEXT_TYPE = 'COMMUNITY'")
    public List<TextsVO> selectAll();

    @Select("SELECT count(*) FROM texts where TEXT_TYPE = 'COMMUNITY'")
    public int communityCount();

    @Select("select * from texts"+" order by text_write_date desc "+
    "offset #{offset} rows fetch next #{limit} rows only")
    public List<TextsVO> selectCommunityTexts(@Param("offset") int offset, @Param("limit") int limit);

    @Insert("insert into texts( text_title, text_content, text_user_no, text_type) values (#{text_title}, #{text_content}, #{user_no}, 'COMMUNITY')")
    public int insertCommunity(@Param("text_title") String text_title, @Param("text_content") String text_content, int user_no);

    @Select("select * from texts where TEXT_ID = #{textId}")
    public TextsVO selectCommunityText(@Param("textId") int textId);

    @Update("UPDATE texts SET text_title = #{text_title}, text_content = #{text_content} WHERE text_id = #{text_id}")
    void updateCommunity(String text_title, String text_content, String text_id);

    @Select("select * from texts where TEXT_ID = #{text_id}")
    TextsVO getTextByID(int text_id);
}
