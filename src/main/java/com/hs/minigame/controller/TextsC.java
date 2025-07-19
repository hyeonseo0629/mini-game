package com.hs.minigame.controller;

import com.hs.minigame.service.login.LoginService;
import com.hs.minigame.service.texts.TextsService;
import com.hs.minigame.vo.TextsVO;
import com.hs.minigame.vo.UsersVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/text")
@Controller
public class TextsC {

    @Autowired
    private TextsService textsService;

    @Autowired
    private LoginService loginService;

    // 게시판 홈 (페이징 있음)
    @GetMapping("/{type}")
    public String textsMain(@RequestParam(defaultValue = "1") int page,
                            @PathVariable String type,
                            Model model) {
        type = type.toUpperCase();
        List<TextsVO> texts = textsService.selectTexts(page, type);
        int totalCount = textsService.textsCount(type);
        int totalPage = (int) Math.ceil((double) totalCount / 10);

        //paging 관련 로직
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("texts", texts);
        model.addAttribute("type", type);
        model.addAttribute("content", "texts/texts_main.jsp");
        return "main_page";
    }

    @ResponseBody
    @GetMapping("/list/{type}/{page}")
    public Map<String, Object> getTextList(@PathVariable String type, @PathVariable int page) {
        type = type.toUpperCase();
        List<TextsVO> texts = textsService.selectTexts(page, type);
        int totalCount = textsService.textsCount(type);
        int totalPage = (int) Math.ceil((double) totalCount / 10);

        Map<String, Object> result = new HashMap<>();
        result.put("texts", texts);
        result.put("totalPage", totalPage);
        result.put("currentPage", page);
        return result;
    }

    // 게시판 글 상세내용
    @GetMapping("/detail/{id}")
    public String textDetail(@PathVariable int id, Model model) {
        TextsVO textVO = textsService.getTextById(id);
        model.addAttribute("textVO", textVO);
        model.addAttribute("content", "texts/texts_detail.jsp");
        return "main_page";
    }

    // 게시판 글 수정 페이지
    @GetMapping("/update/{id}")
    public String textUpdatePage(@PathVariable int id, Model model) {
        TextsVO textVO = textsService.getTextById(id);
        model.addAttribute("textVO", textVO);
        model.addAttribute("content", "texts/texts_update.jsp");
        return "main_page";
    }

    // 게시판 수정
    @ResponseBody
    @PostMapping("/update")
    public int textUpdate(@RequestBody TextsVO textsVO) {
        System.out.println(">>> textUpdate called with: " + textsVO);
        if (textsVO.getText_id() == 0) {
            System.err.println(">>> ERROR: text_id is 0 or missing!");
        }
        textsService.updateText(textsVO);

        return 1;
    }

    // 게시판 글 삭제 (Modified to return JSON and use POST)
    @ResponseBody // Indicate that the return value should be bound to the web response body
    @PostMapping("/delete/{id}/{type}") // Changed to POST method
    public int textDelete(@PathVariable int id, @PathVariable String type) {
        System.out.println("Deleting text with id: " + id + " and type: " + type);
        int result = textsService.deleteText(id);
        return result; // Return 1 for success, 0 for failure
    }

    // 게시판 글 작성 폼
    @GetMapping("/add")
    public String textPost(Model model, HttpSession session) {
        if (loginService.loginCheck(session)) {
            model.addAttribute("content", "texts/texts_post.jsp");
        } else {
            return "redirect:/text/" + model.getAttribute("type");
        }
        return "main_page";
    }

    // 게시판 게시물 작성
    @ResponseBody
    @PostMapping("/add/{type}")
    public int textInsert(HttpSession session, @RequestBody TextsVO textsVO, Model model, @PathVariable String type) {
        System.out.println("---------");
        System.out.println(textsVO.getText_content());
        System.out.println("---------");
        UsersVO user = (UsersVO) session.getAttribute("users");
        if (user == null) return 0;
        textsVO.setText_user_no(user.getUser_no());
        return textsService.insertText(textsVO);

    }
}