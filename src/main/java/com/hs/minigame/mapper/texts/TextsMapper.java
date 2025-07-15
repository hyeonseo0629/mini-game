package com.hs.minigame.mapper.texts;

import com.hs.minigame.vo.TextsVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TextsMapper {

    @Select("select * from texts"+" where text_type = #{type} order by text_write_date desc "+
            "offset #{offset} rows fetch next 5 rows only")
    public List<TextsVO> selectTexts(@Param("offset") int offset,String type);

    @Select("select count(*) from texts where text_type = #{type}")
    public int textsCount(String type);

    @Select("select * from texts where text_id = #{text_id}")
    public TextsVO getTextByID(int text_id);

    @Update("update texts set text_title = #{text_title}, text_content = #{text_content} where text_id = #{text_id}")
    public void updateText(String text_title, String text_content, String text_id);

    @Delete("delete from texts where text_id = #{text_id}")
    public void deleteText(int textId);

    @Insert("insert into texts( text_title, text_content, text_user_no, text_type) values (#{text_title}, #{text_content}, #{user_no}, #{text_type})")
    public void insertText(@Param("text_title") String text_title, @Param("text_content") String text_content, int user_no, String text_type);
}
