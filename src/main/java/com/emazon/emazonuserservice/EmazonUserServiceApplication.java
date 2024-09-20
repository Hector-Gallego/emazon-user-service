package com.emazon.emazonuserservice;

import com.emazon.emazonuserservice.configuration.security.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class EmazonUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmazonUserServiceApplication.class, args);
	}

}
