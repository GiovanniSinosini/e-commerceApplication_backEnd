package com.giosinosini.springboot3.domain.enums;

public enum PaymentStatus {
	
	PENDING (1, "Pending"),
	PAID(2, "Paid"),
	CANCELED (3, "Canceled");

	private int cod;
	private String description;
	
	private PaymentStatus(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return cod;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static PaymentStatus toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		for (PaymentStatus type : PaymentStatus.values()) {
			if (cod.equals(type.cod)) {
				return type;
			}
		}
		throw new IllegalArgumentException("Invalid Id: " + cod);
	}
}