package com.hs.minigame.controller;

import com.hs.minigame.service.community.CommunityService;
import com.hs.minigame.service.login.LoginService;
import com.hs.minigame.service.SampleService;
import com.hs.minigame.vo.LoginVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainC {

    @Autowired
    private SampleService sampleService;

    @Autowired
    private LoginService loginService; //새로운 service마다 의존성 필요

    @Autowired
    private CommunityService communityService;

    @GetMapping("/")
    public String index(Model model) {
        // you can add this as attribute on model if you need.
        // sampleService.selectAll();
        model.addAttribute("content", "game/game_menu.jsp");
        return "main_page";
    }

    @PostMapping("/login")
    public String login(@RequestParam String id, @RequestParam String pw, HttpSession session, Model model) {

        //로그인 제어 / db에서 조회
        LoginVO users = loginService.getUser(id);

        //로그인 확인용 if
        // users.getUser_id(); //db 정보들
        // users.getUser_pw();

         if(users == null){
             System.out.println("id 불일치");
             model.addAttribute("alert","id 불일치");

         }else if(users.getUser_pw().equals(pw)){
             session.setAttribute("users", users);
             System.out.println("로그인 성공");

         }else{
             System.out.println("pw 불일치");
             model.addAttribute("alert","pw 불일치");

         }

//         if(users != null && users.getPw().equals(pw)){
//             session.setAttribute("users", users);
//         }
//-------------------------------------
//         if(users == null){//id 중심 확인 구성이니까 -> db에 없으면 그냥 불일치 시켜버림
//             System.out.println("id 불일치");
//         }else if(users.getPw().equals(pw)) {
//             System.out.println("로그인 성공");
//         }else{
//             System.out.println("pw 불일치");
//         }
        model.addAttribute("content", "game/game_menu.jsp");
        return "main_page";

    }

    @GetMapping("/GameC")
    public String gameC(Model model) {
        model.addAttribute("content", "game/game_menu.jsp");
        model.addAttribute("isGamePage", 1);
        return "main_page";
    }

    @GetMapping("/ShopC")
    public String shopC(Model model) {
        model.addAttribute("content", "shop/shop_main.jsp");
        model.addAttribute("isGamePage", 0);
        return "main_page";
    }
    
    @GetMapping("/RankingC")
    public String rankingC(Model model) {
        model.addAttribute("content", "ranking/ranking_menu.jsp");
        model.addAttribute("isRankingPage", 0);
        return "main_page";
    }

    @GetMapping("/CommunityC")
    public String communityC(Model model) {
        model.addAttribute("community",communityService.getAllReview());
        model.addAttribute("content", "community/community_main.jsp");
        model.addAttribute("isGamePage", 0);
        return "main_page";
    }

    @GetMapping("/NoticeC")
    public String noticeC(Model model) {
        model.addAttribute("content", "notice/notice_main.jsp");
        model.addAttribute("isGamePage", 0);
        return "main_page";
    }

    @GetMapping("/SupportC")
    public String supportC(Model model) {
        model.addAttribute("content", "support/support_main.jsp");
        model.addAttribute("isGamePage", 0);
        return "main_page";
    }
}

