package com.giosinosini.springboot3.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giosinosini.springboot3.domain.Address;
import com.giosinosini.springboot3.domain.City;
import com.giosinosini.springboot3.domain.Client;
import com.giosinosini.springboot3.domain.enums.ClientType;
import com.giosinosini.springboot3.dto.ClientDTO;
import com.giosinosini.springboot3.dto.ClientNewDTO;
import com.giosinosini.springboot3.exceptions.DataIntegrityException;
import com.giosinosini.springboot3.exceptions.ObjectNotFoundException;
import com.giosinosini.springboot3.repositories.AddressRepository;
import com.giosinosini.springboot3.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repo;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public Client find(Integer id) {
		Optional<Client> obj = repo.findById(id); 
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found" + id + ", Type: " + Client.class.getName()));
	}
	
	@Transactional
	public Client insert(Client obj) {
		obj.setId(null);
		obj = repo.save(obj);
		addressRepository.saveAll(obj.getAdresses());
		return obj;
	}
	
	public Client update(Client objPut) {
		Client newObj = find(objPut.getId());
		updateData(newObj, objPut);
		return repo.save(newObj);
	}
	
	private void updateData(Client newObj, Client objPut) {
		newObj.setName(objPut.getName());
		newObj.setEmail(objPut.getEmail());
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);	
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Cannot be deleted because it has related orders");
		}
	}
	
	public List<Client> findAll(){
		return repo.findAll();
	}
	
	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Client fromDTO(ClientDTO objDto) {   // instantiate from the DTO
		return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null, null);
	}
	
	public Client fromDTO(ClientNewDTO objDto) {   
		Client cli = new Client(null, objDto.getName(), objDto.getEmail(), objDto.getNif(), ClientType.toEnum(objDto.getType()), pe.encode(objDto.getPassword()));
		City city = new City(objDto.getCityId(), null, null);
		Address addr = new Address(null, objDto.getStreet(), objDto.getNumber(), objDto.getComplement(), objDto.getDistrict(), objDto.getCod(), cli, city);
		
		cli.getAdresses().add(addr);

		cli.getPhones().add(objDto.getPhone1());
		if (objDto.getPhone2() != null) {
			cli.getPhones().add(objDto.getPhone2());
		}
		if (objDto.getPhone3() != null) {
			cli.getPhones().add(objDto.getPhone3());
		}
		return cli;
	}
}
