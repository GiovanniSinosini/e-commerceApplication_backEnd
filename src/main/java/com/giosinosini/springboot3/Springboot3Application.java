package com.giosinosini.springboot3;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.giosinosini.springboot3.domain.Address;
import com.giosinosini.springboot3.domain.Category;
import com.giosinosini.springboot3.domain.City;
import com.giosinosini.springboot3.domain.Client;
import com.giosinosini.springboot3.domain.Product;
import com.giosinosini.springboot3.domain.State;
import com.giosinosini.springboot3.domain.enums.ClientType;
import com.giosinosini.springboot3.repositories.AddressRepository;
import com.giosinosini.springboot3.repositories.CategoryRepository;
import com.giosinosini.springboot3.repositories.CityRepository;
import com.giosinosini.springboot3.repositories.ClientRepository;
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
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private AddressRepository addressRepository;
	
	
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
		
		Client cli1 = new Client(null, "Manuel Oliveira", "manuel@gmail.com", "123456789", ClientType.PERSON);
		cli1.getPhones().addAll(Arrays.asList("789456123", "654789321"));
		
		Address a1 = new Address(null, "Fifity", "250", "Apart 56 ", "Gold", "321654", cli1, c1);
		Address a2 = new Address(null, "Sexty", "150", "Apart 6 ", "Silver", "123456", cli1, c2);
		
		cli1.getAdresses().addAll(Arrays.asList(a1, a2));
		
		clientRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(a1, a2));
	}
}
