package com.giosinosini.springboot3.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.giosinosini.springboot3.domain.City;
import com.giosinosini.springboot3.domain.State;
import com.giosinosini.springboot3.dto.CityDTO;
import com.giosinosini.springboot3.dto.StateDTO;
import com.giosinosini.springboot3.services.CityService;
import com.giosinosini.springboot3.services.StateService;

@RestController
@RequestMapping(value="/states")
public class StateResource {
	
	@Autowired
	private StateService service;
	
	@Autowired
	private CityService cityService;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<StateDTO>> findAll(){
		List<State> list = service.findAll();
		List<StateDTO> listDto = list.stream().map(state -> new StateDTO(state)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{stateId}/cities", method=RequestMethod.GET)
	public ResponseEntity<List<CityDTO>> findCities(@PathVariable Integer stateId){
		List<City> list = cityService.findByState(stateId);
		List<CityDTO> listDto = list.stream().map(obj -> new CityDTO(obj)).collect(Collectors.toList());  //converts to ListDto
		return ResponseEntity.ok().body(listDto);
	}
	
}
