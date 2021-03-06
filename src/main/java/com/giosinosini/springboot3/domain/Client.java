package com.giosinosini.springboot3.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.giosinosini.springboot3.domain.enums.ClientType;
import com.giosinosini.springboot3.domain.enums.UserProfile;

@Entity
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	
	@Column(unique = true)
	private String email;
	
	private String nif;
	private Integer type;
	
	@JsonIgnore
	private String password;
	
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	private List<Address> adresses = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable(name="PHONE")
	private Set<String> phones = new HashSet<>();
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="USER_PROFILE")
	private Set<Integer> profiles = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "client")
	private List<Request> requests = new ArrayList<>();
	
	public Client(){
		addUserProfile(UserProfile.CLIENT);
	}

	public Client(Integer id, String name, String email, String nif, ClientType type, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.nif = nif;
		this.type = (type == null) ? null : type.getCod();
		this.password = password;
		addUserProfile(UserProfile.CLIENT);
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public ClientType getType() {
		return ClientType.toEnum(type);
	}

	public void setType(ClientType type) {
		this.type = type.getCod();
	}

	public List<Address> getAdresses() {
		return adresses;
	}

	public void setAdresses(List<Address> adresses) {
		this.adresses = adresses;
	}

	public Set<String> getPhones() {
		return phones;
	}

	public void setPhones(Set<String> phones) {
		this.phones = phones;
	}

	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> orders) {
		this.requests = orders;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<UserProfile> getProfiles(){
		return profiles.stream().map(x -> UserProfile.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addUserProfile(UserProfile profile) {
		profiles.add(profile.getCod());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
