package com.giosinosini.springboot3.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.giosinosini.springboot3.domain.Category;
import com.giosinosini.springboot3.dto.CategoryDTO;
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
		
	public Category update(Category objPut) {
		Category newObj = find(objPut.getId());
		updateData(newObj, objPut);
		return repo.save(newObj);
	}
	
	private void updateData(Category newObj, Category objPut) {
		newObj.setName(objPut.getName());
	}
	
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);	
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("You cannot delete a category that has products");
		}
	}
	
	public List<Category> findAll(){
		return repo.findAll();
	}
	
	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Category fromDTO(CategoryDTO objDto) {   // instantiate from the DTO
		return new Category(objDto.getId(), objDto.getName());
	}
	
}
