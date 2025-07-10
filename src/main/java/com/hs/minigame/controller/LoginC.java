package com.hs.minigame.controller;

import com.hs.minigame.service.login.LoginService;
import com.hs.minigame.vo.UsersVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginC {

    @Autowired
    private LoginService loginService; //새로운 service마다 의존성 필요

    @PostMapping("/login")
    public String login(@RequestParam String id, @RequestParam String pw, HttpSession session, Model model) {
        // 로그인 제어 / db에서 조회
        UsersVO users = loginService.getUser(id);

//        if (users==null) {
//            redirectAttributes.addFlashAttribute("alert","id 불일치");
//            redirectAttributes.addFlashAttribute("content", "game/game_menu.jsp");
//            return "redirect:/main_page";
//        } else {
//            if(!pw.equals(users.getUser_pw())){
//                redirectAttributes.addFlashAttribute("alert","pw 불일치");
//                redirectAttributes.addFlashAttribute("content", "game/game_menu.jsp");
//                return "redirect:/main_page";
//            } else {
//                session.setAttribute("users", users);
//                redirectAttributes.addFlashAttribute("alert","로그인 성공");
//                redirectAttributes.addFlashAttribute("content", "game/game_menu.jsp");
//                return  "redirect:/main_page";
//            }
//        }
        if (users == null) { //처음 조건문(참고용)
            System.out.println("id 불일치");
            model.addAttribute("alert","id 불일치");
            model.addAttribute("content", "game/game_menu.jsp");
            return "main_page";
        } else if (users.getUser_pw().equals(pw)) {
            session.setAttribute("users", users);
            System.out.println("로그인 성공");
            model.addAttribute("alert","로그인 성공");
            model.addAttribute("content", "game/game_menu.jsp");
            return "main_page";
        } else {
            System.out.println("pw 불일치");
            model.addAttribute("alert","pw 불일치");
            model.addAttribute("content", "game/game_menu.jsp");
            return "main_page";
        }

    }

    @PostMapping("/logout")
    public String logout(HttpSession session, Model model) {
        session.invalidate();
        model.addAttribute("alert2","로그아웃");
        model.addAttribute("content", "game/game_menu.jsp");
        return "main_page";
    }
}
