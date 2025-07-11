package com.hs.minigame.mapper.support;

import com.hs.minigame.vo.TextsVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

// mapper -> sql
@Mapper
public interface SupportMapper {

//    @Select("select * from texts where TEXT_TYPE = 'COMMUNITY'")
//    public List<TextsVO> selectAll();

    @Select("select * from texts where TEXT_TYPE = 'QUESTION'")
    public  List<TextsVO> selectAllsupport();

    @Insert("insert into texts(text_title, text_content, text_user_no, text_type) values (#{text_title}, #{text_content}, #{users.user_id}, 'QUESTION')")
    public List<TextsVO> insertSupport();

    @Select("SELECT count(*) FROM texts where TEXT_TYPE = 'QUESTION'")
    public int supportCount();

    @Select("select * from texts"+" order by text_id desc"+
            " offset #{offset} rows fetch next #{limit} rows only")
    List<TextsVO> selectSupportTexts(@Param("offset") int offset, @Param("limit") int limit);
}
