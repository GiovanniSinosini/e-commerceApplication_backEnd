package com.giosinosini.springboot3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giosinosini.springboot3.domain.City;
import com.giosinosini.springboot3.repositories.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;
	
	public List<City> findByState(Integer stateId){
		return cityRepository.findCities(stateId);
	}
	
}
