package com.hs.minigame.controller;

import com.hs.minigame.service.community.CommunityService;
import com.hs.minigame.vo.TextsVO;
import com.hs.minigame.vo.UsersVO;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CommunityC {

    @Autowired
    private CommunityService communityService;

    // 게시판 홈 (페이징 있음)
    @GetMapping("/CommunityC")
    public String communityMain(@RequestParam(defaultValue = "1") int page, Model model) {
        int limit = 5;

        List<TextsVO> communityTexts = communityService.selectCommunityTexts(page, limit);
        int totalCount = communityService.communityCount();
        int totalPage = (int) Math.ceil((double) totalCount / limit);

        //paging 관련 로직
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("communityTexts", communityTexts);

        model.addAttribute("content", "community/texts_main.jsp");
        return "main_page";
    }

    // 게시판 글 상세내용
    @GetMapping("/CommunityDetailC")
    public String communityDetail(@RequestParam("text_id") int text_id, Model model) {
        TextsVO text = communityService.selectCommunityText(text_id);
        model.addAttribute("text", text);
        model.addAttribute("content", "community/texts_detail.jsp");
        return"main_page";
    }

    // 게시판 글 작성 폼
    @GetMapping("/CommunityPostC")
    public String communityPostC(Model model) {
        model.addAttribute("content", "community/texts_post.jsp");
        return "main_page";
    }

    // 게시판 글 수정
    @PostMapping("/CommunityUpdatePageC")
    public String updateCommunity(@RequestParam("text") int text_id, Model model) {
        TextsVO textsVO = communityService.getTextById(text_id);
        model.addAttribute("text", textsVO);
        model.addAttribute("content", "community/texts_update.jsp");
        return "main_page";
    }

    @PostMapping("/CommunityUpdateTextC")
    public String updateCommunity(@RequestParam("text_title") String text_title, @RequestParam("text_content") String text_content, @RequestParam("text_id") int text_id, HttpSession httpSession, Model model) {
        UsersVO user = (UsersVO) httpSession.getAttribute("users");

        communityService.updateCommunityText(text_title, text_content, String.valueOf(text_id));
        model.addAttribute("text", communityService.getTextById(text_id));
        model.addAttribute("content", "community/texts_detail.jsp");
        return "main_page";
    }

    // 게시판 글 삭제
    @GetMapping("CommunityDeleteC")
    public String deleteCommunity(@RequestParam("text_id") int text_id, Model model) {
        communityService.deleteCommunityText(text_id);

        model.addAttribute("content", "community/texts_main.jsp");
        return "redirect:/CommunityC";
    }

    // 게시판 게시물 작성
    @PostMapping("/CommunityInsertC")
    public String insertTexts(Model model, HttpSession session, TextsVO texts) {
        UsersVO user = (UsersVO) session.getAttribute("users");
        System.out.println(user);
        int user_no = user.getUser_no();
        System.out.println(user);

        String text_title = texts.getText_title();
        String text_content = texts.getText_content();

        int checkNumber = communityService.insertCommunityText(text_title, text_content, user_no);

        if (checkNumber != 0) {
            System.out.println("업로드 성공!");
            List<TextsVO> list = communityService.getAllReview();
            model.addAttribute("communityTexts", list);
        } else {
            System.out.println("업로드 실패");
        }
        model.addAttribute("content", "community/texts_main.jsp");
        return "redirect:/CommunityC";
    }

//    @GetMapping("/commu-edit-texts")
//    public String showEditForm(@RequestParam("text_id") int text_id, Model model) {
//        TextsVO text = communityService.getTextById(text_id);
//        System.out.println("text : " + text);
//        model.addAttribute("text", text);
//        model.addAttribute("content", "community/texts_detail.jsp");
//        return "main_page"; // community_update.jsp로 이동
//    }
}