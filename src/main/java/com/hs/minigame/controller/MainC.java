package com.hs.minigame.controller;

import com.hs.minigame.service.community.CommunityService;
import com.hs.minigame.service.login.LoginService;
import com.hs.minigame.service.shop.ShopService;
import com.hs.minigame.service.support.SupportService;
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
    private LoginService loginService; //새로운 service마다 의존성 필요

    @Autowired
    private ShopService shopService;

    @Autowired
    private CommunityService communityService;
    @Autowired
    private SupportService supportService;

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
//      }

    @PostMapping("/login")
    public String login(@RequestParam String id, @RequestParam String pw, HttpSession session, Model model) {

        //로그인 제어 / db에서 조회
        LoginVO users = loginService.getUser(id);

//        if(users==null){
//            redirectAttributes.addFlashAttribute("alert","id 불일치");
//            redirectAttributes.addFlashAttribute("content", "game/game_menu.jsp");
//             return "redirect:/main_page";
//        }else{
//            if(!pw.equals(users.getUser_pw())){
//                redirectAttributes.addFlashAttribute("alert","pw 불일치");
//                redirectAttributes.addFlashAttribute("content", "game/game_menu.jsp");
//                return "redirect:/main_page";
//            }else{
//                session.setAttribute("users", users);
//                redirectAttributes.addFlashAttribute("alert","로그인 성공");
//                redirectAttributes.addFlashAttribute("content", "game/game_menu.jsp");
//                return  "redirect:/main_page";
//            }
//        }
         if(users == null){ //처음 조건문(참고용)
             System.out.println("id 불일치");
             model.addAttribute("alert","id 불일치");
             model.addAttribute("content", "game/game_menu.jsp");
             return "main_page";
         }else if(users.getUser_pw().equals(pw)){
             session.setAttribute("users", users);
             System.out.println("로그인 성공");
             model.addAttribute("alert","로그인 성공");
            model.addAttribute("content", "game/game_menu.jsp");
             return "main_page";
        }else{
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

    @GetMapping("/GameC")
    public String gameC(Model model) {
        model.addAttribute("content", "game/game_menu.jsp");
        model.addAttribute("isGamePage", 1);
        return "main_page";
    }



    @GetMapping("/ShopC")
    public String shopC(Model model) {
        model.addAttribute("itemsInfo", shopService.selectAll());
        model.addAttribute("content", "shop/shop_main.jsp");
        model.addAttribute("isGamePage", 0);
        return "main_page";
    }

    @PostMapping("/buyItem")
    public String buyItemC(@RequestParam("itemId") String itemId ,Model model) {
        System.out.println("itemID : " + itemId);
        model.addAttribute("itemsInfo", shopService.selectAll());
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

    @GetMapping("/CommunityPostC")
    public String communityPostC(Model model) {
        model.addAttribute("community",communityService.getAllReview());
        model.addAttribute("content", "community/community_post.jsp");
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
        model.addAttribute("support", supportService.getAllSupport());
        model.addAttribute("content", "support/support_main.jsp");
        model.addAttribute("isGamePage", 0);
        return "main_page";
    }

    @GetMapping("/SupportPostC")
    public String supportPostC(Model model) {
        model.addAttribute("support", supportService.getAllSupport());
        model.addAttribute("content", "support/support_post.jsp");
        model.addAttribute("isGamePage", 0);
        return "main_page";
    }
}

