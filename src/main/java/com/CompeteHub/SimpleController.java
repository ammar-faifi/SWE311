package com.CompeteHub;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SimpleController {
  private static final String template = "You gave %s";
  private final AtomicLong counter = new AtomicLong();

  @GetMapping("/echo")
  public EchoType echo(@RequestParam(value = "msg", defaultValue = "World") String value) {
    return new EchoType(counter.incrementAndGet(),
        String.format(template, value));
  }

  @RequestMapping("/")
  public String test() {
    return "index";
  }

  @GetMapping("/randRR")
  @ResponseBody
  public List<List<String>> generateRoundRobin(@RequestParam(value = "num", defaultValue = "4") int num) {
    System.out.println(num);
    return RoundRobin.generate(num);
  }
}
