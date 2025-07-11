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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
        model.addAttribute("text_title",supportService.setNewSupport());
        model.addAttribute("text_content");
        model.addAttribute("text_user_no");
        model.addAttribute("text_type");
       return "redirect:/SupportC";
   }

    @GetMapping("/support_main")
    public String supportMain(@RequestParam(defaultValue = "1") int page, Model model) {
        //한페이지에 최대 몇개 보여줄건지
        int limit = 5;

        List<TextsVO> supportTexts = supportService.selectSupportTexts(page,limit);
        int totalCount = supportService.supportCount();
        int totalPage = (int)Math.ceil((double)totalCount/limit);

        //paging 관련 로직
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("supportTexts", supportTexts);

        model.addAttribute("content", "support/support_main.jsp");
        model.addAttribute("isGamePage", 0);

        System.out.println(supportService.supportCount());
        return "main_page";
    }

}
