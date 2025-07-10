package com.hs.minigame.controller;

import com.hs.minigame.service.support.SupportService;
import com.hs.minigame.vo.TextsVO;
import com.hs.minigame.vo.UsersVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SupportC {

    @Autowired
    private SupportService supportService;

    @GetMapping("/SupportC")
    public String supportC(Model model) {
        model.addAttribute("support", supportService.getAllSupport());
        model.addAttribute("content", "support/support_main.jsp");
        model.addAttribute("isGamePage", 0);
        return "main_page";
    }

    @GetMapping("/SupportPostC")
    public String supportPostC(Model model) {
        model.addAttribute("content", "support/support_post.jsp");
        model.addAttribute("isGamePage", 0);
        return "main_page";
    }

   @PostMapping("/insert-texts")
    public String insertTexts(Model model, HttpSession session) {
       model.setTextUserNo(((UsersVO) session.getAttribute("users")).getUserNo());
       model.addAttribute("text_title",supportService.setNewSupport());
       model.addAttribute("text_content");
       return "redirect:/SupportC";
   }
}
