package com.hs.minigame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainC {

    @GetMapping("/")
    public String index(Model model) {
        // you can add this as attribute on model if you need.
        // sampleService.selectAll();
        model.addAttribute("content", "game/game_menu.jsp");
        return "main_page";
    }
//    @GetMapping("/main_page") //model과 redirectAttrs.addFlashAttribute 연결용 매핑
//    public String main_page(Model model){
//        if(!model.containsAttribute("content")){
//            model.addAttribute("content", "game/game_menu.jsp");
//        }
//        return "main_page";
//    }

    @GetMapping("/GameC")
    public String gameC(Model model) {
        model.addAttribute("content", "game/game_menu.jsp");
        model.addAttribute("isGamePage", 1);
        return "main_page";
    }
}

