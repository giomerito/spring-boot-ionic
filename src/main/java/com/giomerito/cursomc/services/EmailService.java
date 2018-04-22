package com.giomerito.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.giomerito.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
