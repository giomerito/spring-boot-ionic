package com.giomerito.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.giomerito.cursomc.services.DBService;
import com.giomerito.cursomc.services.EmailService;
import com.giomerito.cursomc.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {

	//Classe de configuração da base de dados de teste
	
	@Autowired
	private DBService dbService;

	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.instantitateDatabase();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
	
	/**
	 * DBService onde contem todos os dados instanciados com os dados inclusos
	 */
}


