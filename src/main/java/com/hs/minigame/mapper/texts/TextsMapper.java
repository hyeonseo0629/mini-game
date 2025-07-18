package com.hs.minigame.mapper.texts;

import com.hs.minigame.vo.TextsVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TextsMapper {

    @Select("select t.*, u.user_nickname from texts t, users u where t.text_user_no = u.user_no and text_type = #{type} order by text_write_date desc "+
            "offset #{offset} rows fetch next 5 rows only")
    public List<TextsVO> selectTexts(int offset,String type);

    @Select("select count(*) from texts where text_type = #{type}")
    public int textsCount(String type);

    @Select("SELECT\n" +
            "    t.*,\n" +
            "    CASE t.text_type\n" +
            "        WHEN 'COMMUNITY' THEN '게시판'\n" +
            "        WHEN 'NOTICE' THEN '공지사항'\n" +
            "        WHEN 'QUESTION' THEN '문의사항'\n" +
            "        ELSE '기타'\n" +
            "        END AS text_kr\n" +
            "FROM TEXTS t where text_id = #{text_id}")
    public TextsVO getTextByID(int text_id);

    @Update("update texts set text_title = #{text_title}, text_content = #{text_content} where text_id = #{text_id}")
    public void updateText(TextsVO textsVO);

    @Delete("delete from texts where text_id = #{text_id}")
    public void deleteText(int textId);

    @Insert("insert into texts( text_title, text_content, text_user_no, text_type) values (#{text_title}, #{text_content}, #{text_user_no}, #{text_type})")
    public int insertText(TextsVO textsVO);

    @Select("SELECT * FROM texts WHERE text_type = #{type} ORDER BY text_id DESC LIMIT #{perPage} OFFSET #{start}")
    List<TextsVO> getTextsByPage(@Param("type") String type, @Param("start") int start, @Param("perPage") int perPage);

    @Select("SELECT COUNT() FROM texts WHERE text_type = #{type}")
    int getTotalCount(@Param("type") String type);
}
