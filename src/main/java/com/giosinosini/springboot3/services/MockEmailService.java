package com.giosinosini.springboot3.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService {

	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Simulating email sending...");
		LOG.info(msg.toString());
		LOG.info("Email sent.");
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("Simulating HTML email sending...");
		LOG.info(msg.toString());
		LOG.info("Email sent.");
		
	}

}
