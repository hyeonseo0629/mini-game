package com.hs.minigame.controller;

import com.hs.minigame.service.community.CommunityService;
import com.hs.minigame.vo.TextsVO;
import com.hs.minigame.vo.UsersVO;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.apache.catalina.User;
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

    @GetMapping("/community_main")
    public String communityC(Model model) {
        model.addAttribute("community", communityService.getAllReview());
        model.addAttribute("content", "community/community_main.jsp");
        return "main_page";
    }

    @GetMapping("/CommunityPostC")
    public String communityPostC(Model model) {
        model.addAttribute("content", "community/community_post.jsp");
        return "main_page";
    }

    @PostMapping("/CommunityUpdateC")
    public String updateCommunity(@RequestParam("text") int text_id, Model model) {
        System.out.println(text_id);
        TextsVO textsVO = communityService.getTextById(text_id);
        model.addAttribute("text", textsVO);
//      System.out.println("text_id: " + text.getText_id());
        model.addAttribute("content", "community/community_update.jsp");
        return "main_page";
    }

    @PostMapping("/commu-insert-texts")
    public String insertTexts(Model model, HttpSession session, TextsVO texts) {
        UsersVO user = (UsersVO) session.getAttribute("users");

        int user_no = user.getUser_no();
        String text_title = texts.getText_title();
        String text_content = texts.getText_content();

        int checkNumber = communityService.setNewCommunity(text_title, text_content, user_no);

        model.addAttribute("content", "community/community_main.jsp");
        return "main_page";
    }

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

        model.addAttribute("content", "community/community_main.jsp");
        return "main_page";
    }

    @GetMapping("/community_detail")
    public String communityDetail(@Param("text_id") int text_id, Model model) {
        TextsVO text = communityService.selectCommunityText(text_id);

        model.addAttribute("text", text);
        model.addAttribute("content", "community/community_detail.jsp");

        return"main_page";
    }

    @GetMapping("/commu-edit-texts")
    public String showEditForm(@RequestParam("text_id") int text_id, Model model) {
        TextsVO text = communityService.getTextById(text_id);
        System.out.println("text : " + text);
        model.addAttribute("text", text);
        model.addAttribute("content", "community/community_detail.jsp");
        return "main_page"; // community_update.jsp로 이동
    }

    @PostMapping("/commu-update-texts")
    public String updateCommunity(@RequestParam("text_title") String text_title, @RequestParam("text_content") String text_content, @RequestParam("text_id") int text_id, HttpSession httpSession, Model model) {
        UsersVO user = (UsersVO) httpSession.getAttribute("users");
//        int id = Integer.parseInt(text_id);

        System.out.println(text_title);
        System.out.println(text_content);
        System.out.println(text_id);

        communityService.updateCommunity(text_title, text_content, String.valueOf(text_id));
        model.addAttribute("text", communityService.getTextById(text_id));
        model.addAttribute("content", "community/community_detail.jsp");
        return "main_page";
    }

}
