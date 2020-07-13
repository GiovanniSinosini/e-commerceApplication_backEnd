package com.giosinosini.springboot3.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.giosinosini.springboot3.domain.enums.PaymentStatus;

@Entity
@JsonTypeName("payment_BankSlip")
public class Payment_BankSlip extends Payment {
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dueDate;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date PaymentDate;
	
	public Payment_BankSlip() {
	}

	public Payment_BankSlip(Integer id, PaymentStatus status, Request order, Date dueDate, Date paymenteDate) {
		super(id, status, order);
        this.dueDate = dueDate;
        this.PaymentDate = paymenteDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPaymentDate() {
		return PaymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		PaymentDate = paymentDate;
	}
		
}
