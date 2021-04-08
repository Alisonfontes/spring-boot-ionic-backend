package com.alisonshow.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.alisonshow.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendorderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}
