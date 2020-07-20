package com.giosinosini.springboot3.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.giosinosini.springboot3.domain.enums.UserProfile;

public class UserSpringSecurity implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;  //profile list
	
	public UserSpringSecurity() {
		
	}
	
	public UserSpringSecurity(Integer id, String email, String password,Set<UserProfile> profile) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.authorities = profile.stream().map(x -> new SimpleGrantedAuthority(x.getDescription())).collect(Collectors.toList());
	}


	public Integer getId() {
		return id;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public boolean hasRole(UserProfile profile) {  // checks if a user has a certain profile
		return getAuthorities().contains(new SimpleGrantedAuthority(profile.getDescription())); 
	}

}
