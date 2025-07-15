package com.hs.minigame.mapper.shop;

import com.hs.minigame.vo.ShopItemsVO;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// mapper -> sql
@Mapper
public interface ShopMapper {

    @Select("""
    SELECT s.ITEM_ID, s.ITEM_NAME, s.ITEM_AVATAR_IMG
    FROM BUYING_RECORD b, SHOPITEMS s
    WHERE b.BUYING_ITEM_ID = s.ITEM_ID AND b.BUYING_USER_NO = #{user_no}""")
    public List<ShopItemsVO> inventory(int user_no);


    @Select("select * from SHOPITEMS")
    public List<ShopItemsVO> selectAll();

    @Update("UPDATE SHOPITEMS SET ITEM_AVATAR_IMG=#{item_avatar_img} WHERE ITEM_ID=#{item_id}")
    public void updateImage(ShopItemsVO vo);

    @Select("select * from SHOPITEMS"+" order by ITEM_ID desc"+
            " offset #{offset} rows fetch next #{limit} rows only")
    public List<ShopItemsVO> SelectShopItemsByPaging(@Param("offset") int offset,@Param("limit") int limit);

    @Select("SELECT count(*) FROM shopitems")
    public int getShopItemCount();

    @Select("select * from SHOPITEMS where ITEM_ID = #{itemId}")
    public ShopItemsVO selectInfoById(@Param("itemId") int itemId);

    @Update("update USERS set USER_MONEY = USER_MONEY - #{itemPrice} where USER_ID = #{userId} ")
    public int updateUserInfo(@Param("userId") String userId, @Param("itemPrice")  int price);

    //구매 후 보유 돈 조회하기
    @Select("select USER_MONEY from USERS where USER_ID = #{userId}")
    public int getAfterMoney(@Param("userId") String userId);

    @Insert("insert into BUYING_RECORD(BUYING_USER_NO,BUYING_ITEM_ID,BUYING_DATE) values (#{user_no},#{item_Id},SYSDATE)")
    public int insertRecord(@Param("user_no") int user_no, @Param("item_Id") int item_Id);

    @Update("update USERS set USER_AVATAR_IMG = #{avatarImg} where USER_ID = #{userId}")
    public int updateAvatar(String userId, String avatarImg);
}
