package com.hs.minigame.mapper.shop;

import com.hs.minigame.vo.ShopItemsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// mapper -> sql
@Mapper
public interface ShopMapper {

    @Select("select * from shopitems")
    public List<ShopItemsVO> selectAll();

    @Update("UPDATE SHOPITEMS SET ITEM_AVATAR_IMG=#{item_avatar_img} WHERE ITEM_ID=#{item_id}")
    void updateImage(ShopItemsVO vo);

    @Select("select * from shopitems"+" order by item_id desc"+
            " offset #{offset} rows fetch next #{limit} rows only")
    List<ShopItemsVO> SelectShopItemsByPaging(@Param("offset") int offset,@Param("limit") int limit);

    @Select("SELECT count(*) FROM shopitems")
    public int getShopItemCount();


    @Select("select * from SHOPITEMS where ITEM_ID = #{itemId}")
    ShopItemsVO selectInfoById(@Param("itemId") String itemId);

    @Update("update USERS set USER_MONEY = USER_MONEY - #{itemPrice} where USER_ID = #{userId} ")
    int updateUserInfo(@Param("userId") String userId, @Param("itemPrice")  int price);



    //구매 후 보유 돈 조회하기
    @Select("select USER_MONEY from USERS where USER_ID = #{userId}")
    int getAfterMoney(@Param("userId") String userId);
}
