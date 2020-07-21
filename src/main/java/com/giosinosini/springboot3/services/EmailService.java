package com.giosinosini.springboot3.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.giosinosini.springboot3.domain.Client;
import com.giosinosini.springboot3.domain.Request;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Request obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Request obj);
	
	void sendHtmlEmail(MimeMessage msg);
	
	void  sendNewPasswordEmail(Client client, String newPass);
}
