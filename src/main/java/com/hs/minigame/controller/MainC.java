package com.hs.minigame.controller;

import com.hs.minigame.service.game.GameService;
import com.hs.minigame.vo.UsersVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String gameC(Model model) {
        model.addAttribute("content", "game/game_menu.jsp");
        return "main_page";
    }

    @GetMapping("/GameEndC")
    public String gameEnd(@RequestParam("result") String result, HttpSession session, Model model) {
        UsersVO user = (UsersVO) session.getAttribute("users");
        int user_no = user.getUser_no();

        // result 값에 따라 if문으로 처리 -> service로 이동
        if (user_no == 0) {
            result = "noneId";
        } else {
//            if (result.equals("win")) {
//                if (gameService.selectScoreCount(user_no) != 0) { // score 테이블에 레이팅 값 존재
//                    gameService.updateWinScore(user_no);
//                } else {
//                    gameService.insertWinScore(user_no);
//                }
//                if (gameService.selectWinStack(user_no) != 0) { // score 테이블에 연승 값 존재
//                    gameService.updateWinStack(user_no);
//                } else {
//                    gameService.insertWinStack(user_no);
//                }
//            } else if (result.equals("draw")) {
//            } else {
//                if (gameService.selectScoreCount(user_no) != 0) { // score 테이블에 레이팅 값 존재
//                    gameService.
//                }
//            }
//            gameService.updateMoney(user_no);
        }

        model.addAttribute("content", "game/game_menu.jsp?result=" + result);
        return "main_page";
    }
}

