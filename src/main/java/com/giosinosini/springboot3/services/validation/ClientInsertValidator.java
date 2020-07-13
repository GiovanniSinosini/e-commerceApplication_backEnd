package com.giosinosini.springboot3.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.giosinosini.springboot3.domain.Client;
import com.giosinosini.springboot3.domain.enums.ClientType;
import com.giosinosini.springboot3.dto.ClientNewDTO;
import com.giosinosini.springboot3.repositories.ClientRepository;
import com.giosinosini.springboot3.resources.exceptions.FieldMessage;
import com.giosinosini.springboot3.services.validation.utils.PT;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {
	
	@Autowired
	private ClientRepository repo;
	
	@Override
	public void initialize(ClientInsert ann) {
	}

	@Override
	public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getType().equals(ClientType.PERSON.getCod()) && !PT.isValidNIF(objDto.getNif())){
			list.add(new FieldMessage("nif", "Invalid NIF"));
		}
		
		Client aux = repo.findByEmail(objDto.getEmail());
		if (aux != null) {
			list.add(new FieldMessage("email", "Error: existing email"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
	
	
}