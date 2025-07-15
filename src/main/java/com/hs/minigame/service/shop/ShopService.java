package com.hs.minigame.service.shop;

import com.hs.minigame.mapper.shop.ShopMapper;
import com.hs.minigame.vo.ShopItemsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// model
@Service
public class ShopService {

    @Autowired
    private ShopMapper shopMapper;

    public List<ShopItemsVO> getInventory(int user_no) {
        List<ShopItemsVO> inventoryList = shopMapper.inventory(user_no);
        return inventoryList;
    }

    public ShopItemsVO getItemById(int itemId) {
        ShopItemsVO itemGetById = shopMapper.selectInfoById(itemId);
        return itemGetById;
    }

    public List<ShopItemsVO> selectAll() {
        List<ShopItemsVO> items = shopMapper.selectAll();
        return items;
    }

    public List<ShopItemsVO> getShopItems(int page, int limit) {
        int offset = (page - 1) * limit;
        return shopMapper.SelectShopItemsByPaging(offset, limit);
    }

    public int getShopItemCount() {
        return shopMapper.getShopItemCount();
    }

    public int buyitem(String userId, int itemPrice) {
        return shopMapper.updateUserInfo(userId, itemPrice);
    }

    //구매 후 보유 돈 조회하기
    public int getUserMoney(String userId) {
        return shopMapper.getAfterMoney(userId);
    }

    public int insertBuyingRecord(int user_no, int item_Id) {
        return shopMapper.insertRecord(user_no, item_Id);
    }

    public int updateUserAvatar(String userId, String avatarImg) {
        return shopMapper.updateAvatar(userId, avatarImg);
    }
}
