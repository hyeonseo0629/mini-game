package com.hs.minigame.mapper.support;

import com.hs.minigame.vo.TextsVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

// mapper -> sql
@Mapper
public interface SupportMapper {

//    @Select("select * from texts where TEXT_TYPE = 'COMMUNITY'")
//    public List<TextsVO> selectAll();

    @Select("select * from texts where TEXT_TYPE = 'QUESTION'")
    public  List<TextsVO> selectAllsupport();

    @Insert("insert into texts(text_title, text_content, text_user_no, text_type) values (#{text_title}, #{text_content}, #{text_user_no}, 'QUESTION')")
    public List<TextsVO> insertSupport();
}
