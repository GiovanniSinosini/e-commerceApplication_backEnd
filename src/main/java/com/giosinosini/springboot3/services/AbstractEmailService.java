package com.giosinosini.springboot3.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.giosinosini.springboot3.domain.Client;
import com.giosinosini.springboot3.domain.Request;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
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

	protected String htmlFromTemplatePedido(Request obj) {
		Context context = new Context();
		context.setVariable("request", obj);
		return templateEngine.process("email/requestConfirmation", context);
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Request obj) {
		
		try {
		MimeMessage mm = prepareMimeMessageFromRequest(obj);
		sendHtmlEmail(mm);
		}
		catch (MessagingException e){
			sendOrderConfirmationEmail(obj);
		}
	}

	private MimeMessage prepareMimeMessageFromRequest(Request obj) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(obj.getClient().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Request Confirmation. Id: " + obj.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplatePedido(obj), true);
		return mimeMessage;
	}
	
	@Override
	public void  sendNewPasswordEmail(Client client, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(client, newPass);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Client client, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(client.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Request for new password.");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("New password => " + newPass + "\nChange this password at the first opportunity.");
		return sm;
	}
	
}
