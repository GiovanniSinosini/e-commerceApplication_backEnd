package com.giosinosini.springboot3.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.giosinosini.springboot3.domain.enums.PaymentStatus;

@Entity
public class Payment_BankSlip extends Payment {
	private static final long serialVersionUID = 1L;
	
	@Temporal(TemporalType.DATE)
	private Date dueDate;
	
	@Temporal(TemporalType.DATE)
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
