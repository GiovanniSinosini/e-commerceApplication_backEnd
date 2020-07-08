package com.giosinosini.springboot3.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giosinosini.springboot3.domain.Request;
import com.giosinosini.springboot3.exceptions.ObjectNotFoundException;
import com.giosinosini.springboot3.repositories.RequestRepository;

@Service
public class RequestService {

	@Autowired
	private RequestRepository repo;
	
	public Request find(Integer id) {
		Optional<Request> obj = repo.findById(id); 
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found" + id + ", Type: " + Request.class.getName()));
	}
	
}
