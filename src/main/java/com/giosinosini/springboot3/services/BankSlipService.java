package com.giosinosini.springboot3.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.giosinosini.springboot3.domain.Payment_BankSlip;

@Service
public class BankSlipService {
	
	public void fillPayment_BankSlip(Payment_BankSlip pay, Date instantRequest) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instantRequest);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pay.setDueDate(cal.getTime());
	
	}
}
