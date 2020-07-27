package com.giosinosini.springboot3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giosinosini.springboot3.domain.State;
import com.giosinosini.springboot3.repositories.StateRepository;

@Service
public class StateService {
	
	@Autowired
	private StateRepository stateRepository;
	
	public List<State> findAll(){
		return stateRepository.findAllByOrderByName();
	}
}
