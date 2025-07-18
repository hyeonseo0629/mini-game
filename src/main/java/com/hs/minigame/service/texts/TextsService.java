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

    public void updateText(TextsVO textsVO) {
        textsMapper.updateText(textsVO);
    }

    public int deleteText(int textId) {
        textsMapper.deleteText(textId);
        return textId;
    }

    public int insertText(TextsVO textsVO) {
        return textsMapper.insertText(textsVO);
    }

    public List<TextsVO> getTextsByPage(String type, int start, int perPage) {
        return textsMapper.getTextsByPage(type, start, perPage);
    }

    public int getTotalCount(String type) {
        return textsMapper.getTotalCount(type);
    }

}
