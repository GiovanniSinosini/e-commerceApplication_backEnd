package com.giosinosini.springboot3.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giosinosini.springboot3.domain.Client;
import com.giosinosini.springboot3.domain.Payment_BankSlip;
import com.giosinosini.springboot3.domain.Request;
import com.giosinosini.springboot3.domain.RequestItem;
import com.giosinosini.springboot3.domain.enums.PaymentStatus;
import com.giosinosini.springboot3.repositories.PaymentRepository;
import com.giosinosini.springboot3.repositories.RequestItemRepository;
import com.giosinosini.springboot3.repositories.RequestRepository;
import com.giosinosini.springboot3.security.UserSpringSecurity;
import com.giosinosini.springboot3.services.exceptions.AuthorizationException;
import com.giosinosini.springboot3.services.exceptions.ObjectNotFoundException;

@Service
public class RequestService {

	@Autowired
	private RequestRepository repo;
	
	@Autowired
	private BankSlipService bankSlipService; 
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private RequestItemRepository requestItemRepository;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private EmailService emailService;
	
	public Request find(Integer id) {
		Optional<Request> obj = repo.findById(id); 
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found" + id + ", Type: " + Request.class.getName()));
	}
	
	@Transactional
	public Request insert(Request obj) {
		obj.setId(null);
		obj.setInstant(new Date());
		obj.setClient(clientService.find(obj.getClient().getId()));
		obj.getPayment().setStatus(PaymentStatus.PENDING);
		obj.getPayment().setRequest(obj);
		if (obj.getPayment() instanceof Payment_BankSlip) {
			Payment_BankSlip payment = (Payment_BankSlip) obj.getPayment();
			bankSlipService.fillPayment_BankSlip(payment, obj.getInstant());
		}
		obj = repo.save(obj);
		paymentRepository.save(obj.getPayment());
		for(RequestItem ri : obj.getItems()) {
			ri.setDiscount(0.0);
			ri.setProduct(productService.find(ri.getProduct().getId()));
			ri.setPrice(ri.getProduct().getPrice());
			ri.setRequest(obj);
		}
		requestItemRepository.saveAll(obj.getItems());
		emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
	}
	
	public Page<Request> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		
		UserSpringSecurity currentUser = UserService.authenticated();
		if (currentUser == null) {
			throw new AuthorizationException("Access denied");
		}
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Client client = clientService.find(currentUser.getId());  // client logged
		return repo.findByClient(client, pageRequest);  // returns logged client orders 
	}
}
