package com.hs.minigame.controller;

import com.hs.minigame.service.notice.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoticeC {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/NoticeC")
    public String noticeC(Model model) {
        model.addAttribute("notice", noticeService.getAllNotice());
        model.addAttribute("content", "notice/notice_main.jsp");
        return "main_page";
    }
}
