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

    public List<ShopItemsVO> selectAll() {
        List<ShopItemsVO> items = shopMapper.selectAll();
        return items;
    }

}
