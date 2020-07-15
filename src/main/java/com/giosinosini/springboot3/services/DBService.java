package com.giosinosini.springboot3.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class DBService {

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

	public void instantiateTestDatabase() throws ParseException {
		
		Category cat1 = new Category(null, "Computing");
		Category cat2 = new Category(null, "Office");
		Category cat3 = new Category(null, "Car");
		Category cat4 = new Category(null, "Library");
		Category cat5 = new Category(null, "Food");
		Category cat6 = new Category(null, "Clothes");
		Category cat7 = new Category(null, "Pet");
				
		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 250.00);
		Product p3 = new Product(null, "Mouse", 20.00);
		Product p4 = new Product(null, "Table", 75.00);
		Product p5 = new Product(null, "T-shirt", 20.00);
		Product p6 = new Product(null, "Colinary Book", 15.00);
		Product p7 = new Product(null, "Keyboard", 55.00);
		Product p8 = new Product(null, "Rice", 1.00);
		Product p9 = new Product(null, "Dog food", 12.00);
		Product p10 = new Product(null, "Chair", 45.00);
		Product p11 = new Product(null, "Wheels", 20.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
		cat2.getProducts().addAll(Arrays.asList(p2, p4));
		cat3.getProducts().addAll(Arrays.asList(p11));
		cat4.getProducts().addAll(Arrays.asList(p6));
		cat5.getProducts().addAll(Arrays.asList(p8));
		cat6.getProducts().addAll(Arrays.asList(p5));
		cat7.getProducts().addAll(Arrays.asList(p9));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		p4.getCategories().addAll(Arrays.asList(cat2));
		p5.getCategories().addAll(Arrays.asList(cat6));
		p6.getCategories().addAll(Arrays.asList(cat4));
		p7.getCategories().addAll(Arrays.asList(cat1));
		p8.getCategories().addAll(Arrays.asList(cat5));
		p9.getCategories().addAll(Arrays.asList(cat7));
		p10.getCategories().addAll(Arrays.asList(cat2));
		p11.getCategories().addAll(Arrays.asList(cat3));
				
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		State state1 = new State(null, "Porto");
		State state2 = new State(null, "Lisboa");
		
		City c1 = new City(null, "Maia", state1);
		City c2 = new City(null, "Cascais", state2);
		City c3 = new City(null, "Sintra", state2);
		
		state1.getCities().addAll(Arrays.asList(c1));
		state2.getCities().addAll(Arrays.asList(c2, c3));
		
		stateRepository.saveAll(Arrays.asList(state1, state2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Client cli1 = new Client(null, "Manuel Oliveira", "carvalho.sino@gmail.com", "123456789", ClientType.PERSON);
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
