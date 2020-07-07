package com.giosinosini.springboot3.domain;

import javax.persistence.Entity;

import com.giosinosini.springboot3.domain.enums.PaymentStatus;

@Entity
public class Payment_Card extends Payment{
	private static final long serialVersionUID = 1L;

	private Integer numberInstallments;
	
	public Payment_Card() {
	}

	public Payment_Card(Integer id, PaymentStatus status, Request order, Integer numberInstallments) {
		super(id, status, order);
		this.numberInstallments = numberInstallments; 
	}

	public Integer getNumberInstallments() {
		return numberInstallments;
	}

	public void setNumberInstallments(Integer numberInstallments) {
		this.numberInstallments = numberInstallments;
	}
		
}
