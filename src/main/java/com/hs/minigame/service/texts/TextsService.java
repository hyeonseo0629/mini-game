package com.hs.minigame.service.texts;

import com.hs.minigame.mapper.texts.TextsMapper;
import com.hs.minigame.vo.TextsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TextsService {
    @Autowired
    private TextsMapper textsMapper;

    public List<TextsVO> selectTexts(int page, String type) {
        int offset = (page - 1) * 5;
        return textsMapper.selectTexts(offset, type);
    }

    public int textsCount(String type) {
        return textsMapper.textsCount(type);
    }

    public TextsVO getTextById(int textId) {
        return textsMapper.getTextByID(textId);
    }

    public void updateText(String textTitle, String textContent, String s) {
        textsMapper.updateText(textTitle, textContent, s);
    }

    public void deleteText(int textId) {
        textsMapper.deleteText(textId);
    }

    public void insertText(String textTitle, String textContent, int userNo, String textType) {
        textsMapper.insertText(textTitle, textContent, userNo, textType);
    }
}
