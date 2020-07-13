package com.giosinosini.springboot3.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.giosinosini.springboot3.domain.Product;
import com.giosinosini.springboot3.dto.ProductDTO;
import com.giosinosini.springboot3.resources.utils.URL;
import com.giosinosini.springboot3.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

	@Autowired
	private ProductService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> find(@PathVariable Integer id) {
		Product obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ProductDTO>> findPage(
			
			@RequestParam(value="name", defaultValue = "")String name, 
			@RequestParam(value="categories", defaultValue = "")String categories, 
			@RequestParam(value="page", defaultValue = "0")Integer page, 
			@RequestParam(value="linesPerPage", defaultValue = "24")Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue = "name")String orderBy, 
			@RequestParam(value="direction", defaultValue = "ASC")String direction) {
		String nameDedoded = URL.decodeParam(name); 
		List<Integer> ids = URL.decodeIntList(categories);
		Page<Product> list = service.search(nameDedoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProductDTO> listDTO = list.map(obj -> new ProductDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
}
