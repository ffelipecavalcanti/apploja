package com.applojamc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.applojamc.services.DBService;
import com.applojamc.services.EmailService;
import com.applojamc.services.SmtpEmailService;

@Configuration
@Profile("prod")
public class ProdConfig {

	@Autowired
	private DBService dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	@Bean
	public boolean instantiateDatabase() throws ParseException {

		if ("create".equals(strategy)) {

			dbService.instatiateTestDatabase();
			return true;
		}

		return false;
	}

	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
