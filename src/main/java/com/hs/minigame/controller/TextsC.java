package com.hs.minigame.controller;

import com.hs.minigame.service.texts.TextsService;
import com.hs.minigame.vo.TextsVO;
import com.hs.minigame.vo.UsersVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TextsC {

    @Autowired
    private TextsService textsService;

    // 게시판 홈 (페이징 있음)
    @GetMapping("/TextsC")
    public String textsMain(@RequestParam(defaultValue = "1") int page, @RequestParam("b") int text_type, Model model) {
        int limit = 5;
        String type = "";
        String KRType = "";

        switch (text_type) {
            case 1:
                type = "COMMUNITY";
                KRType = "게시판";
                break;
            case 2:
                type = "NOTICE";
                KRType = "공지사항";
                break;
            case 3:
                type = "QUESTION";
                KRType = "문의사항";
                break;
        }

        List<TextsVO> Texts = textsService.selectTexts(page, limit, type);
        int totalCount = textsService.textsCount(type);
        int totalPage = (int) Math.ceil((double) totalCount / limit);

        //paging 관련 로직
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("Texts", Texts);
        model.addAttribute("texts_type", KRType);
        model.addAttribute("content", "texts/texts_main.jsp");
        return "main_page";
    }

    // 게시판 글 상세내용
    @GetMapping("/TextDetailC")
    public String textDetail(@RequestParam("text_id") int text_id, Model model) {
        TextsVO text = textsService.getTextById(text_id);
        model.addAttribute("text", text);
        model.addAttribute("content", "texts/texts_detail.jsp");
        return"main_page";
    }

    // 게시판 글 작성 폼
    @GetMapping("/TextPostC")
    public String textPost(Model model) {
        model.addAttribute("content", "texts/texts_post.jsp");
        return "main_page";
    }

    // 게시판 글 수정 페이지
    @PostMapping("/TextUpdatePageC")
    public String textUpdatePage(@RequestParam("text") int text_id, Model model) {
        TextsVO textsVO = textsService.getTextById(text_id);
        model.addAttribute("text", textsVO);
        model.addAttribute("content", "texts/texts_update.jsp");
        return "main_page";
    }

    // 게시판 수정
    @PostMapping("/TextUpdateC")
    public String textUpdate(@RequestParam("text_title") String text_title, @RequestParam("text_content") String text_content, @RequestParam("text_id") int text_id, HttpSession httpSession, Model model) {
        UsersVO user = (UsersVO) httpSession.getAttribute("users");

        textsService.updateText(text_title, text_content, String.valueOf(text_id));
        model.addAttribute("text", textsService.getTextById(text_id));
        model.addAttribute("content", "texts/texts_detail.jsp");
        return "main_page";
    }

    // 게시판 글 삭제
    @GetMapping("TextDeleteC")
    public String textDelete(@RequestParam("text_id") int text_id, Model model) {
        textsService.deleteText(text_id);

        model.addAttribute("content", "texts/texts_main.jsp");
        return "redirect:/TextsC";
    }

    // 게시판 게시물 작성
    @PostMapping("/TextInsertC")
    public String textInsert(Model model, HttpSession session, TextsVO texts) {
        UsersVO user = (UsersVO) session.getAttribute("users");
        int user_no = user.getUser_no();

        String text_title = texts.getText_title();
        String text_content = texts.getText_content();
        String text_type = texts.getText_type();

        int checkNumber = textsService.insertText(text_title, text_content, user_no, text_type);

        if (checkNumber != 0) {
            System.out.println("업로드 성공!");
//            List<TextsVO> list = textsService.getAllReview();
//            model.addAttribute("textsTexts", list);
        } else {
            System.out.println("업로드 실패");
        }
        model.addAttribute("content", "texts/texts_main.jsp");
        return "redirect:/TextsC";
    }

//    @GetMapping("/commu-edit-texts")
//    public String showEditForm(@RequestParam("text_id") int text_id, Model model) {
//        TextsVO text = textsService.getTextById(text_id);
//        System.out.println("text : " + text);
//        model.addAttribute("text", text);
//        model.addAttribute("content", "texts/texts_detail.jsp");
//        return "main_page"; // texts_update.jsp로 이동
//    }
}