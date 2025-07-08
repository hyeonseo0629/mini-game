package com.hs.minigame.controller;

import com.hs.minigame.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainC {

    @Autowired
    private SampleService sampleService;

    @GetMapping("/")
    public String index() {
        // you can add this as attribute on model if you need.
        // sampleService.selectAll();
        return "index";
    }

    @GetMapping("/GameC")
    public String gameC() {
        return "game/game_menu";
    }

}
