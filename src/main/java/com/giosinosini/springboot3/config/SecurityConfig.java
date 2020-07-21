package com.giosinosini.springboot3.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.giosinosini.springboot3.security.JWTAuthenticationFilter;
import com.giosinosini.springboot3.security.JWTAuthorizationFilter;
import com.giosinosini.springboot3.security.JWTUtil;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService; 
	
	@Autowired
	private Environment env;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	private static final String[] PUBLIC_MATCHERS = { //free access
		"/h2-console/**",
	};
	
	private static final String[] PUBLIC_MATCHERS_GET = { 
			"/products/**",
			"/categories/**"
			
		};
	
	private static final String[] PUBLIC_MATCHERS_POST = {  
			"clients/**",
			"/auth/forgot/**"
		};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		if (Arrays.asList(env.getActiveProfiles()).contains("test")){  
			http.headers().frameOptions().disable(); // free access to the H2 database(test)
		}
		
		http.cors().and().csrf().disable();
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()   // just readings for everyone
		.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()   // just readings for everyone
		.antMatchers(PUBLIC_MATCHERS).permitAll()   //free access
		.anyRequest().authenticated();    // requires authentication
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //don't create session
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());  // default configuration
		return source;
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {  // save encrypted password
		return new BCryptPasswordEncoder();
	}
	
}
