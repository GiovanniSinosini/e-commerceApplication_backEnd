package com.giosinosini.springboot3;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.giosinosini.springboot3.domain.Category;
import com.giosinosini.springboot3.repositories.CategoryRepository;

@SpringBootApplication
public class Springboot3Application implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;   //dependency
	
	public static void main(String[] args) {
		SpringApplication.run(Springboot3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Computing");
		Category cat2 = new Category(null, "Office");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		
	}

}
