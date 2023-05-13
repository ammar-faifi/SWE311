package com.CompeteHub;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SimpleController {
    @RequestMapping("/")
    public String test() {
        return "tournaments";
    }

    @RequestMapping("/createTour")
    public String createTour() {
        return "createTour";
    }

    @RequestMapping("/login")
    public String login() {
        return "loginPage";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "firstPage";
    }

    @RequestMapping("/upcommingTournaments")
    public String UpcommingTournaments() {
        return "UpcommingTournaments";
    }

    @RequestMapping("/student")
    public String student() {
        return "tournaments";
    }
}
