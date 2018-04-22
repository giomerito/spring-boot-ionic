package com.giomerito.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.giomerito.cursomc.services.DBService;
import com.giomerito.cursomc.services.EmailService;
import com.giomerito.cursomc.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {

	//Classe de configuração da base de dados de desenvolvimento
	
	@Autowired
	private DBService dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		
		//Verifica se a opção do base de dados está como create
		if(!"create".equals(strategy)) {
			return false;
		}
		dbService.instantitateDatabase();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
