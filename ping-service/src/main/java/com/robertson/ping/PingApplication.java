package com.robertson.ping;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableBinding(PingProducer.class)
public class PingApplication {

	public static void main(String[] args) {

		SpringApplication.run(PingApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(PingService service) {
		return args -> {
			service.sendPingMessage();
		};
	}
}
