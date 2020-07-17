package com.giosinosini.springboot3.domain.enums;

public enum UserProfile {
	
	ADMIN (1, "ROLE_ADMIN"),
	CLIENT(2, "ROLE_CLIENT");

	private int cod;
	private String description;
	
	private UserProfile(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return cod;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static UserProfile toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		for (UserProfile type : UserProfile.values()) {
			if (cod.equals(type.cod)) {
				return type;
			}
		}
		throw new IllegalArgumentException("Invalid Id: " + cod);
	}
}