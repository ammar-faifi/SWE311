package com.CompeteHub;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
	private static final String template = "You gave %s";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/echo")
	public EchoType echo(@RequestParam(value = "msg", defaultValue = "World") String value) {
		return new EchoType(counter.incrementAndGet(), String.format(template, value));
	}

}
