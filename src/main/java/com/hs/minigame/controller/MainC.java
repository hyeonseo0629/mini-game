package com.hs.minigame.controller;

import com.hs.minigame.mapper.login.LoginMapper;
import com.hs.minigame.service.game.GameService;
import com.hs.minigame.vo.UsersVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

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

    @Autowired
    private GameService gameService;

    @GetMapping("/GameC")
    public String gameC(Model model, HttpSession session) {
        UsersVO user = (UsersVO) session.getAttribute("users");
        user.setUser_money(gameService.getUserMoney(user.getUser_no()));

        model.addAttribute("content", "game/game_menu.jsp");
        return "main_page";
    }

    @PostMapping("/GameEndC")
    public String gameEnd(@RequestParam("result") String result, HttpSession session, RedirectAttributes redirectAttributes) {
        UsersVO user = (UsersVO) session.getAttribute("users");

        if (user == null || user.getUser_no() == 0) {
            redirectAttributes.addFlashAttribute("result", "No user");
        } else {
            int user_no = user.getUser_no();
            Map<String, Integer> resultMap = gameService.processGameResult(user_no, result);

            redirectAttributes.addFlashAttribute("result", result);
            redirectAttributes.addFlashAttribute("rewardMoney", resultMap.get("rewardMoney"));
            redirectAttributes.addFlashAttribute("stackPoint", resultMap.get("stackPoint"));
        }

        redirectAttributes.addFlashAttribute("content", "game/game_menu.jsp");
        return "redirect:/main_page";
    }
}

