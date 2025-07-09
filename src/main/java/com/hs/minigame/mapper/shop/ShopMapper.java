package com.hs.minigame.mapper.shop;

import com.hs.minigame.vo.SampleVO;
import com.hs.minigame.vo.ShopVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

// mapper -> sql
@Mapper
public interface ShopMapper {

    @Select("select * from shopitems")
    public List<ShopVO> selectAll();

    @Update("UPDATE SHOPITEMS SET ITEM_AVATAR_IMG=#{item_avatar_img} WHERE ITEM_ID=#{item_id}")
    void updateImage(ShopVO vo);
}
