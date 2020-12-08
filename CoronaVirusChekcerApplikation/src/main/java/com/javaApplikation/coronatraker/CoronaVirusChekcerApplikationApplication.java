package com.javaApplikation.coronatraker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoronaVirusChekcerApplikationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoronaVirusChekcerApplikationApplication.class, args);
	}

}
