package com.dhruvsharma845.k8spatterns.ambassador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AmbassadorApplication {

	@Autowired
	private StringRedisTemplate redisTemplate;

	public static void main(String[] args) {
		SpringApplication.run(AmbassadorApplication.class, args);
	}

	@GetMapping("/greeting")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		redisTemplate.opsForList().rightPush("mykey", name);
		String value = redisTemplate.opsForList().getFirst("mykey");
		System.out.println(String.format("Value read from redis %s", value));
		return String.format("Hello %s!", value);
	}

}
