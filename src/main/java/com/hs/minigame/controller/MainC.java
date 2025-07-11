package com.hs.minigame.controller;

import com.hs.minigame.vo.UsersVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class MainC {

    @GetMapping("/")
    public String index(Model model) {
        // you can add this as attribute on model if you need.
        // sampleService.selectAll();
        model.addAttribute("content", "game/game_menu.jsp");
        return "main_page";
    }

    @GetMapping("/main_page")
    public String mainPage(HttpSession session, Model model) {
        String alert = (String) model.asMap().get("alert");
        String alert2 = (String) model.asMap().get("alert2");
        String content = (String) model.asMap().get("content");

        model.addAttribute("alert", alert);
        model.addAttribute("alert2", alert2);
        model.addAttribute("content", content != null ? content : "game/game_menu.jsp");
        //model.addAttribute("content", content);

        UsersVO user = (UsersVO) session.getAttribute("users");
        model.addAttribute("user", user);

        return "main_page";
    }
    @GetMapping("/GameC")
    public String gameC(Model model) {
        model.addAttribute("content", "game/game_menu.jsp");
        model.addAttribute("isGamePage", 1);
        return "main_page";
    }
}

