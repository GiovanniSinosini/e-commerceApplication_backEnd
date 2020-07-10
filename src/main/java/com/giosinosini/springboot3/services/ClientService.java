package com.giosinosini.springboot3.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.giosinosini.springboot3.domain.Client;
import com.giosinosini.springboot3.dto.ClientDTO;
import com.giosinosini.springboot3.exceptions.DataIntegrityException;
import com.giosinosini.springboot3.exceptions.ObjectNotFoundException;
import com.giosinosini.springboot3.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repo;
	
	public Client find(Integer id) {
		Optional<Client> obj = repo.findById(id); 
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found" + id + ", Type: " + Client.class.getName()));
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
			throw new DataIntegrityException("Cannot be deleted because it has related entities");
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
		return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null);
	}
		
}
