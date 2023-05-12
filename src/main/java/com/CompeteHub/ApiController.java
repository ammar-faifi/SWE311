package com.CompeteHub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    private static final String template = "You gave %s";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/echo")
    public EchoType echo(@RequestParam(value = "msg", defaultValue = "World") String value) {
        return new EchoType(counter.incrementAndGet(),
                String.format(template, value));
    }

    @GetMapping("/randRR")
    public List<List<String>> generateRoundRobin(@RequestParam(value = "num", defaultValue = "4") int num) {
        System.out.println(num);
        return RoundRobin.generate(num, null);
    }

    @PostMapping("/createTour")
    public List<List<String>> createTour(@RequestBody TournamentModel body) {
        System.out.println(">>>>>>> Creating Tour.");

        try {
            if (body.getTeams() == "") {
                // Assuming numOfTeams not null
                return RoundRobin.generate(body.getNumOfTeams(), null);
            } else {
                // Assume it has correct length, type, and format
                List<String> teams = Arrays.asList(body.getTeams().split(","));
                return RoundRobin.generate(teams.size(), teams);
            }
        } catch (Throwable e) {
            System.out.println("Error " + e.getMessage());
            return null;
        }
    }
}
