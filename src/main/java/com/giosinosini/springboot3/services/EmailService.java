package com.giosinosini.springboot3.services;

import org.springframework.mail.SimpleMailMessage;

import com.giosinosini.springboot3.domain.Request;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Request obj);
	
	void sendEmail(SimpleMailMessage msg);

}
