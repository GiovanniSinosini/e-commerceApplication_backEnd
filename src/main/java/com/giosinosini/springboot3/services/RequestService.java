package com.giosinosini.springboot3.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giosinosini.springboot3.domain.Payment_BankSlip;
import com.giosinosini.springboot3.domain.Request;
import com.giosinosini.springboot3.domain.RequestItem;
import com.giosinosini.springboot3.domain.enums.PaymentStatus;
import com.giosinosini.springboot3.exceptions.ObjectNotFoundException;
import com.giosinosini.springboot3.repositories.PaymentRepository;
import com.giosinosini.springboot3.repositories.RequestItemRepository;
import com.giosinosini.springboot3.repositories.RequestRepository;

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
	
}
