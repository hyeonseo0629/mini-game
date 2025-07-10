package com.hs.minigame.controller;

import com.hs.minigame.service.community.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommunityC {

    @Autowired
    private CommunityService communityService;

    @GetMapping("/CommunityC")
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
}
