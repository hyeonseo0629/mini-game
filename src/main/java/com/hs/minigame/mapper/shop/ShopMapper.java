package com.hs.minigame.mapper.shop;

import com.hs.minigame.vo.ShopVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

// mapper -> sql
@Mapper
public interface ShopMapper {

    @Select("select * from shopitems")
    public List<ShopVO> selectAll();
}
