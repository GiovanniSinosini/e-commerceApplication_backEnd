package com.giosinosini.springboot3;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.giosinosini.springboot3.domain.Category;
import com.giosinosini.springboot3.domain.City;
import com.giosinosini.springboot3.domain.Product;
import com.giosinosini.springboot3.domain.State;
import com.giosinosini.springboot3.repositories.CategoryRepository;
import com.giosinosini.springboot3.repositories.CityRepository;
import com.giosinosini.springboot3.repositories.ProductRepository;
import com.giosinosini.springboot3.repositories.StateRepository;

@SpringBootApplication
public class Springboot3Application implements CommandLineRunner {

	//dependencies
	@Autowired
	private CategoryRepository categoryRepository;   
	@Autowired 
	private ProductRepository productRepository;
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private CityRepository cityRepository;

	
	public static void main(String[] args) {
		SpringApplication.run(Springboot3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Computing");
		Category cat2 = new Category(null, "Office");
		
		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 250.00);
		Product p3 = new Product(null, "Mouse", 20.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
				
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		State state1 = new State(null, "Porto");
		State state2 = new State(null, "Lisboa");
		
		City c1 = new City(null, "Maia", state1);
		City c2 = new City(null, "Cascais", state2);
		City c3 = new City(null, "Sintra", state2);
		
		state1.getCities().addAll(Arrays.asList(c1));
		state2.getCities().addAll(Arrays.asList(c2, c3));
		
		stateRepository.saveAll(Arrays.asList(state1, state2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));
		
	}
}
