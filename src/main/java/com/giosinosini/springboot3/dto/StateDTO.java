package com.giosinosini.springboot3.dto;

import java.io.Serializable;

import com.giosinosini.springboot3.domain.State;

public class StateDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	
	public StateDTO() {
	}

	public StateDTO(State state) {
		super();
		this.id = state.getId();
		this.name = state.getName();
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
