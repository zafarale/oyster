package uk.co.cirquare.oyster;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import uk.co.cirquare.oyster.core.service.barrier.BarrierService;

@SpringBootApplication
public class OysterApplication {

	public static void main(String[] args) {
		SpringApplication.run(OysterApplication.class, args);
	}
	/*
	@Bean
	CommandLineRunner runner(BarrierService barrierService) {
		return args ->{
			barrierService.create()
		}
	}*/
}
