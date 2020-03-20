package com.applojamc.services;

import org.springframework.mail.SimpleMailMessage;

import com.applojamc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);

	void sendEmail(SimpleMailMessage msg);
}
