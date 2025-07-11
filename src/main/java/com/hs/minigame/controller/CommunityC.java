package com.hs.minigame.controller;

import com.hs.minigame.service.community.CommunityService;
import com.hs.minigame.vo.TextsVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CommunityC {

    @Autowired
    private CommunityService communityService;

    @GetMapping("/community_main")
    public String communityC(Model model) {
        model.addAttribute("community",communityService.getAllReview());
        model.addAttribute("content", "community/community_main.jsp");
        model.addAttribute("isGamePage", 0);
        return "main_page";
    }

    @GetMapping("/CommunityPostC")
    public String communityPostC(Model model) {
        model.addAttribute("community",communityService.getAllReview());
        model.addAttribute("content", "community/community_post.jsp");
        model.addAttribute("isGamePage", 0);
        return "main_page";
    }

    @PostMapping("/commu-insert-texts")
    public String insertTexts(Model model, HttpSession session) {
        model.addAttribute("text_title",communityService.setNewCommunity());
        model.addAttribute("text_content");
        model.addAttribute("text_user_no");
        model.addAttribute("text_type");
        return "redirect:/CommunityC";
    }

    @GetMapping("/CommunityC")
    public String communityMain(@RequestParam(defaultValue = "1") int page, Model model) {
        int limit = 5;

        List<TextsVO> communityTexts = communityService.selectCommunityTexts(page, limit);
        int totalCount = communityService.communityCount();
        int totalPage = (int)Math.ceil((double)totalCount / limit);

        //paging 관련 로직
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("communityTexts", communityTexts);

        model.addAttribute("content", "community/community_main.jsp");
        model.addAttribute("isGamePage", 0);

        return "main_page";
    }

}
