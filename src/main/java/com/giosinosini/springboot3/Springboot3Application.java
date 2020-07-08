package com.giosinosini.springboot3;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.giosinosini.springboot3.domain.Address;
import com.giosinosini.springboot3.domain.Category;
import com.giosinosini.springboot3.domain.City;
import com.giosinosini.springboot3.domain.Client;
import com.giosinosini.springboot3.domain.Payment;
import com.giosinosini.springboot3.domain.Payment_BankSlip;
import com.giosinosini.springboot3.domain.Payment_Card;
import com.giosinosini.springboot3.domain.Product;
import com.giosinosini.springboot3.domain.Request;
import com.giosinosini.springboot3.domain.RequestItem;
import com.giosinosini.springboot3.domain.State;
import com.giosinosini.springboot3.domain.enums.ClientType;
import com.giosinosini.springboot3.domain.enums.PaymentStatus;
import com.giosinosini.springboot3.repositories.AddressRepository;
import com.giosinosini.springboot3.repositories.CategoryRepository;
import com.giosinosini.springboot3.repositories.CityRepository;
import com.giosinosini.springboot3.repositories.ClientRepository;
import com.giosinosini.springboot3.repositories.PaymentRepository;
import com.giosinosini.springboot3.repositories.ProductRepository;
import com.giosinosini.springboot3.repositories.RequestItemRepository;
import com.giosinosini.springboot3.repositories.RequestRepository;
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
	@Autowired
	private RequestRepository orderRepository;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private RequestItemRepository requestItemRepository;
	
	
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
		Address a2 = new Address(null, "Sixty", "150", "Apart 6 ", "Silver", "123456", cli1, c2);
		
		cli1.getAdresses().addAll(Arrays.asList(a1, a2));
		
		clientRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(a1, a2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
		
		Request ord1 = new Request(null, sdf.parse("25/12/2019 10:05"), cli1, a1);
		Request ord2 = new Request(null, sdf.parse("10/09/2019 19:13"), cli1, a2);
		
		Payment pay1 = new Payment_Card(null, PaymentStatus.PAID, ord1, 6);
		ord1.setPayment(pay1);
		
		Payment pay2 = new Payment_BankSlip(null, PaymentStatus.PENDING, ord2, sdf.parse("20/09/2019 00:00"), null);
		ord2.setPayment(pay2);
		
		cli1.getRequests().addAll(Arrays.asList(ord1, ord2));
		
		orderRepository.saveAll(Arrays.asList(ord1, ord2));
		paymentRepository.saveAll(Arrays.asList(pay1, pay2));
		
		RequestItem rI1 = new RequestItem(ord1, p1, 0.00, 1, 2000.00);
		RequestItem rI2 = new RequestItem(ord1, p3, 0.00, 2, 20.00);
		RequestItem rI3 = new RequestItem(ord2, p2, 100.00, 1, 250.00);
		
		ord1.getItems().addAll(Arrays.asList(rI1, rI2));   // association between request and RequestItens
		ord2.getItems().addAll(Arrays.asList(rI3));
		
		p1.getItems().addAll(Arrays.asList(rI1));   // association between product and RequestItem
		p2.getItems().addAll(Arrays.asList(rI3));
		p3.getItems().addAll(Arrays.asList(rI2));
		
		requestItemRepository.saveAll(Arrays.asList(rI1,rI2,rI3));
	}
}
