package com.hs.minigame.mapper.community;

import com.hs.minigame.vo.TextsVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

// mapper -> sql
@Mapper
public interface CommunityMapper {

//    @Select("select * from texts where TEXT_TYPE = 'COMMUNITY'")
//    public List<TextsVO> selectAll();

    @Select("select * from texts where text_type = 'COMMUNITY'")
    public List<TextsVO> selectAll();

    @Select("select count(*) from texts where text_type = 'COMMUNITY'")
    public int communityCount();

    @Select("select * from texts"+" order by text_write_date desc "+
    "offset #{offset} rows fetch next #{limit} rows only")
    public List<TextsVO> selectCommunityTexts(@Param("offset") int offset, @Param("limit") int limit);

    @Insert("insert into texts( text_title, text_content, text_user_no, text_type) values (#{text_title}, #{text_content}, #{user_no}, 'COMMUNITY')")
    public int insertCommunityText(@Param("text_title") String text_title, @Param("text_content") String text_content, int user_no);

    @Select("select * from texts where text_id = #{textId}")
    public TextsVO selectCommunityText(@Param("textId") int textId);

    @Update("update texts set text_title = #{text_title}, text_content = #{text_content} where text_id = #{text_id}")
    public void updateCommunityText(String text_title, String text_content, String text_id);

    @Select("select * from texts where text_id = #{text_id}")
    public TextsVO getTextByID(int text_id);

    @Delete("delete from texts where text_id = #{text_id}")
    public void deleteCommunityText(int textId);
}
