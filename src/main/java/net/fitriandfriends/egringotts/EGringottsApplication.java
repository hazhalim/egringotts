package net.fitriandfriends.egringotts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EGringottsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EGringottsApplication.class, args);
	}

}