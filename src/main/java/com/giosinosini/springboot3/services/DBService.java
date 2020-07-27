package com.giosinosini.springboot3.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.giosinosini.springboot3.domain.enums.UserProfile;
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
	@Autowired
	private BCryptPasswordEncoder pe;

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
		
		Product p12 = new Product(null, "Product 12", 10.00);
		Product p13 = new Product(null, "Product 13", 10.00);
		Product p14 = new Product(null, "Product 14", 10.00);
		Product p15 = new Product(null, "Product 15", 10.00);
		Product p16 = new Product(null, "Product 16", 10.00);
		Product p17 = new Product(null, "Product 17", 10.00);
		Product p18 = new Product(null, "Product 18", 10.00);
		Product p19 = new Product(null, "Product 19", 10.00);
		Product p20 = new Product(null, "Product 20", 10.00);
		Product p21 = new Product(null, "Product 21", 10.00);
		Product p22 = new Product(null, "Product 22", 10.00);
		Product p23 = new Product(null, "Product 23", 10.00);
		Product p24 = new Product(null, "Product 24", 10.00);
		Product p25 = new Product(null, "Product 25", 10.00);
		Product p26 = new Product(null, "Product 26", 10.00);
		Product p27 = new Product(null, "Product 27", 10.00);
		Product p28 = new Product(null, "Product 28", 10.00);
		Product p29 = new Product(null, "Product 29", 10.00);
		Product p30 = new Product(null, "Product 30", 10.00);
		Product p31 = new Product(null, "Product 31", 10.00);
		Product p32 = new Product(null, "Product 32", 10.00);
		Product p33 = new Product(null, "Product 33", 10.00);
		Product p34 = new Product(null, "Product 34", 10.00);
		Product p35 = new Product(null, "Product 35", 10.00);
		Product p36 = new Product(null, "Product 36", 10.00);
		Product p37 = new Product(null, "Product 37", 10.00);
		Product p38 = new Product(null, "Product 38", 10.00);
		Product p39 = new Product(null, "Product 39", 10.00);
		Product p40 = new Product(null, "Product 40", 10.00);
		Product p41 = new Product(null, "Product 41", 10.00);
		Product p42 = new Product(null, "Product 42", 10.00);
		Product p43 = new Product(null, "Product 43", 10.00);
		Product p44 = new Product(null, "Product 44", 10.00);
		Product p45 = new Product(null, "Product 45", 10.00);
		Product p46 = new Product(null, "Product 46", 10.00);
		Product p47 = new Product(null, "Product 47", 10.00);
		Product p48 = new Product(null, "Product 48", 10.00);
		Product p49 = new Product(null, "Product 49", 10.00);
		Product p50 = new Product(null, "Product 50", 10.00);
		
		cat1.getProducts().addAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
		p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
		p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
		
		
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
		p12.getCategories().addAll(Arrays.asList(cat1));
		p13.getCategories().addAll(Arrays.asList(cat1));
		p14.getCategories().addAll(Arrays.asList(cat1));
		p15.getCategories().addAll(Arrays.asList(cat1));
		p16.getCategories().addAll(Arrays.asList(cat1));
		p17.getCategories().addAll(Arrays.asList(cat1));
		p18.getCategories().addAll(Arrays.asList(cat1));
		p19.getCategories().addAll(Arrays.asList(cat1));
		p20.getCategories().addAll(Arrays.asList(cat1));
		p21.getCategories().addAll(Arrays.asList(cat1));
		p22.getCategories().addAll(Arrays.asList(cat1));
		p23.getCategories().addAll(Arrays.asList(cat1));
		p24.getCategories().addAll(Arrays.asList(cat1));
		p25.getCategories().addAll(Arrays.asList(cat1));
		p26.getCategories().addAll(Arrays.asList(cat1));
		p27.getCategories().addAll(Arrays.asList(cat1));
		p28.getCategories().addAll(Arrays.asList(cat1));
		p29.getCategories().addAll(Arrays.asList(cat1));
		p30.getCategories().addAll(Arrays.asList(cat1));
		p31.getCategories().addAll(Arrays.asList(cat1));
		p32.getCategories().addAll(Arrays.asList(cat1));
		p33.getCategories().addAll(Arrays.asList(cat1));
		p34.getCategories().addAll(Arrays.asList(cat1));
		p35.getCategories().addAll(Arrays.asList(cat1));
		p36.getCategories().addAll(Arrays.asList(cat1));
		p37.getCategories().addAll(Arrays.asList(cat1));
		p38.getCategories().addAll(Arrays.asList(cat1));
		p39.getCategories().addAll(Arrays.asList(cat1));
		p40.getCategories().addAll(Arrays.asList(cat1));
		p41.getCategories().addAll(Arrays.asList(cat1));
		p42.getCategories().addAll(Arrays.asList(cat1));
		p43.getCategories().addAll(Arrays.asList(cat1));
		p44.getCategories().addAll(Arrays.asList(cat1));
		p45.getCategories().addAll(Arrays.asList(cat1));
		p46.getCategories().addAll(Arrays.asList(cat1));
		p47.getCategories().addAll(Arrays.asList(cat1));
		p48.getCategories().addAll(Arrays.asList(cat1));
		p49.getCategories().addAll(Arrays.asList(cat1));
		p50.getCategories().addAll(Arrays.asList(cat1));
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
		cat2.getProducts().addAll(Arrays.asList(p2, p4));
		cat3.getProducts().addAll(Arrays.asList(p11));
		cat4.getProducts().addAll(Arrays.asList(p6));
		cat5.getProducts().addAll(Arrays.asList(p8));
		cat6.getProducts().addAll(Arrays.asList(p5));
		cat7.getProducts().addAll(Arrays.asList(p9));
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		productRepository.saveAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
				p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
				p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
		
		State state1 = new State(null, "Porto");
		State state2 = new State(null, "Lisboa");
		
		City c1 = new City(null, "Maia", state1);
		City c2 = new City(null, "Cascais", state2);
		City c3 = new City(null, "Sintra", state2);
		
		state1.getCities().addAll(Arrays.asList(c1));
		state2.getCities().addAll(Arrays.asList(c2, c3));
		
		stateRepository.saveAll(Arrays.asList(state1, state2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Client cli1 = new Client(null, "Manuel Oliveira", "carvalho.sino@gmail.com", "537.567.520-03", ClientType.PERSON, pe.encode("123"));
		cli1.getPhones().addAll(Arrays.asList("789456123", "654789321"));
		
		Client cli2 = new Client(null, "José Silva", "sinosini@hotmail.com", "811.075.620-40", ClientType.PERSON, pe.encode("123"));
		cli2.getPhones().addAll(Arrays.asList("5469872", "8563214"));
		cli2.addUserProfile(UserProfile.ADMIN);
		
		Address a1 = new Address(null, "Fifity", "250", "Apart 56 ", "Gold", "321654", cli1, c1);
		Address a2 = new Address(null, "Sixty", "150", "Apart 6 ", "Silver", "123456", cli1, c2);
		Address a3 = new Address(null, "Ten", "14", "Apart 4 ", "Bronze", "321654", cli2, c2);

		
		cli1.getAdresses().addAll(Arrays.asList(a1, a2));
		cli2.getAdresses().addAll(Arrays.asList(a3));
		
		clientRepository.saveAll(Arrays.asList(cli1, cli2));
		addressRepository.saveAll(Arrays.asList(a1, a2, a3));
		
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
