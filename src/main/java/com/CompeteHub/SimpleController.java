package com.CompeteHub;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SimpleController {
    @RequestMapping("/")
    public String test() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "loginPage";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "tournaments";
    }

    @RequestMapping("/student")
    public String student() {
        return "firstPage";
    }
}
