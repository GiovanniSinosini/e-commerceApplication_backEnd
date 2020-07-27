package com.giosinosini.springboot3.dto;

import java.io.Serializable;

import com.giosinosini.springboot3.domain.City;

public class CityDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	
	public CityDTO() {
	}

	public CityDTO(City city) {
		super();
		this.id = city.getId();
		this.name = city.getName();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
