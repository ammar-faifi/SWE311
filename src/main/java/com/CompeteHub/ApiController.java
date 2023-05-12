package com.CompeteHub;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        return RoundRobin.generate(num);
    }
}
