package com.giosinosini.springboot3.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.giosinosini.springboot3.domain.Category;
import com.giosinosini.springboot3.exceptions.DataIntegrityException;
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
	
	public Category insert(Category obj) {
		obj.setId(null);  // if the id exists, it will just be an update
		return repo.save(obj);
	}
	
	public Category update(Category obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);	
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("You cannot delete a category that has products");
		}
	}
}
