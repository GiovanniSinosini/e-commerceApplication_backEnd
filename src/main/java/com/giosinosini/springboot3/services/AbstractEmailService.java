package com.giosinosini.springboot3.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.giosinosini.springboot3.domain.Request;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Request obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
	}
	
	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Request obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getClient().getEmail());  // receiver
		sm.setFrom(sender);  // sender
		sm.setSubject("Confirmed request. Code: " + obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis())); // server time
		sm.setText(obj.toString());
		return sm;
	}
}
