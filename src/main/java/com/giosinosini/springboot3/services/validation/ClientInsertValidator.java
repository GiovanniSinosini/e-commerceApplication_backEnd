package com.giosinosini.springboot3.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.giosinosini.springboot3.domain.enums.ClientType;
import com.giosinosini.springboot3.dto.ClientNewDTO;
import com.giosinosini.springboot3.resources.exceptions.FieldMessage;
import com.giosinosini.springboot3.services.validation.utils.PT;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {
	@Override
	public void initialize(ClientInsert ann) {
	}

	@Override
	public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getType().equals(ClientType.PERSON.getCod()) && !PT.isValidNIF(objDto.getNif())){
			list.add(new FieldMessage("nif", "Invalid NIF"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}