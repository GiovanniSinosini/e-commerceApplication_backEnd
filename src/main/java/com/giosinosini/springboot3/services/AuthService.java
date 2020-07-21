package com.giosinosini.springboot3.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.giosinosini.springboot3.domain.Client;
import com.giosinosini.springboot3.exceptions.ObjectNotFoundException;
import com.giosinosini.springboot3.repositories.ClientRepository;

@Service
public class AuthService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private EmailService emailService;
	
	private Random rand = new Random();
	
	
	public void sendNewPassword(String email) {
		
		Client client = clientRepository.findByEmail(email);
		if (client == null) {
			throw new ObjectNotFoundException("Email not found.");
		}
		
		String newPass = newPassword();
		client.setPassword(bCryptPasswordEncoder.encode(newPass));
		
		clientRepository.save(client);
		emailService.sendNewPasswordEmail(client, newPass);
	}

	private String newPassword() {   // create random password

		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) {  //  generates a digit
			return (char) (rand.nextInt(10) + 48);
		}
		else if (opt == 1) {  // generates a capital letter
			return (char) (rand.nextInt(26) + 65);
		}
		else {  // generates a lowercase letter
			return (char) (rand.nextInt(26) + 97);
		}
	}

}
