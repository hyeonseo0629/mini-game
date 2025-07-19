package com.hs.minigame.mapper.texts;

import com.hs.minigame.vo.TextsVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TextsMapper {

    @Select("""
                SELECT t.*, u.user_nickname
                FROM texts t, users u
                WHERE t.text_user_no = u.user_no 
                  AND text_type = #{type}
                ORDER BY text_write_date DESC
                OFFSET #{offset} ROWS FETCH NEXT 10 ROWS ONLY
            """)
    List<TextsVO> selectTexts(@Param("type") String type, @Param("offset") int offset);

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

    @Insert("insert into texts( text_title, text_content, text_user_no, text_type) " +
            "values (#{text_title}, #{text_content}, #{text_user_no}, #{text_type})")
    public int insertText(TextsVO textsVO);

    @Select("SELECT * FROM texts WHERE text_type = #{type} ORDER BY text_id DESC LIMIT #{perPage} OFFSET #{start}")
    public List<TextsVO> getTextsByPage(@Param("type") String type, @Param("start") int start, @Param("perPage") int perPage);

    @Select("SELECT COUNT() FROM texts WHERE text_type = #{type}")
    public int getTotalCount(@Param("type") String type);
}
