package com.hs.minigame.controller;

import com.hs.minigame.service.login.LoginService;
import com.hs.minigame.vo.UsersVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginC {

    @Autowired
    private LoginService loginService; //새로운 service마다 의존성 필요

    @PostMapping("/login")
    public String login(@RequestParam String id, @RequestParam String pw, HttpSession session,HttpServletRequest request, RedirectAttributes redirectAttributes) {
        // 로그인 제어 / db에서 조회
        UsersVO users = loginService.getUser(id);
        String referer = request.getHeader("Referer");
        //"Referer" : 사용자가 이 요청을 보내기 전 머물렀던 페이지URL
        if (users == null) {
            System.out.println("id 불일치");
            redirectAttributes.addFlashAttribute("alert","id 불일치");
            redirectAttributes.addFlashAttribute("content", "game/game_menu.jsp");
            return "redirect:/main_page";
        } else if (users.getUser_pw().equals(pw)) {
            session.setAttribute("users", users);
            System.out.println("로그인 성공");
            redirectAttributes.addFlashAttribute("alert","로그인 성공");
            redirectAttributes.addFlashAttribute("content", "game/game_menu.jsp");
            redirectAttributes.addFlashAttribute("user", users);
            return "redirect:" + (referer != null ? referer : "/default_page");
            //삼항연산자 활용 : 조건 ? 참일 때 값(referer) : 거짓일 때 값("/default_page")
            // referer 값있으면 그 페이지로 / 없으면 "/default_page"로
        } else {
            System.out.println("pw 불일치");
            redirectAttributes.addFlashAttribute("alert","pw 불일치");
            redirectAttributes.addFlashAttribute("content", "game/game_menu.jsp");
            return "redirect:/main_page";
        }
//        if (users == null) {model용
//            System.out.println("id 불일치");
//            model.addAttribute("alert","id 불일치");
//            model.addAttribute("content", "game/game_menu.jsp");
//            return "main_page";
//        } else if (users.getUser_pw().equals(pw)) {
//            session.setAttribute("users", users);
//            System.out.println("로그인 성공");
//            model.addAttribute("alert","로그인 성공");
//            model.addAttribute("content", "game/game_menu.jsp");
//            model.addAttribute("user", users);
//            return "main_page";
//        } else {
//        System.out.println("pw 불일치");
//           model.addAttribute("alert","pw 불일치");
//            model.addAttribute("content", "game/game_menu.jsp");
//            return "main_page";
//        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {

        session.invalidate();
        redirectAttributes.addFlashAttribute("alert2","로그아웃");
        redirectAttributes.addFlashAttribute("content", "game/game_menu.jsp");
        return "redirect:/main_page";

//        session.invalidate();model용
//        model.addAttribute("alert2","로그아웃");
//        model.addAttribute("content", "game/game_menu.jsp");
//        return "main_page";
    }
    @PostMapping("/sign")
    public String sign(@ModelAttribute UsersVO users,RedirectAttributes redirectAttributes) {

        users.setUser_money(5000);
        users.setUser_role("USER");
        users.setUser_avatar_img("base_avatar.webp");

      boolean success = loginService.registerUser(users);

       if(success) {
           redirectAttributes.addFlashAttribute("alert","회원 가입 성공");
       } else {
           redirectAttributes.addFlashAttribute("alert","회원 가입 실패");
       }
       redirectAttributes.addFlashAttribute("content", "game/game_menu.jsp");
       return  "redirect:/login";
    }
    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("user_id") String userId, HttpSession session, RedirectAttributes redirectAttributes ) {
        System.out.println("Controller: Attempting to delete user with ID: " + userId);
        loginService.deleteUser(userId);
        session.invalidate();
        redirectAttributes.addFlashAttribute("alert","회원 탈퇴가 완료되었습니다.");
        return "redirect:/main_page";
    }
}
