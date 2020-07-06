package com.giosinosini.springboot3.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giosinosini.springboot3.domain.Category;
import com.giosinosini.springboot3.exceptions.ObjectNotFoundException;
import com.giosinosini.springboot3.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;
	
	public Category find(Integer id) {
		Optional<Category> obj = repo.findById(id); 
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found" + id + ", Type: " + Category.class.getName()));
	}
	
}
