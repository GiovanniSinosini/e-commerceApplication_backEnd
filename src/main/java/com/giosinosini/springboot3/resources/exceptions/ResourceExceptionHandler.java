package com.giosinosini.springboot3.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.giosinosini.springboot3.services.exceptions.AuthorizationException;
import com.giosinosini.springboot3.services.exceptions.DataIntegrityException;
import com.giosinosini.springboot3.services.exceptions.FileException;
import com.giosinosini.springboot3.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)  // handling for a given class
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Not found", e.getMessage(), request.getRequestURI()); 
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(DataIntegrityException.class)  
	public ResponseEntity<StandardError> DataIntegrityException(DataIntegrityException e, HttpServletRequest request){
		
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Error: data integrity", e.getMessage(), request.getRequestURI()); 
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)  
	public ResponseEntity<StandardError> MethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request){
		
		ValidationError err = new ValidationError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Validation Error", e.getMessage(), request.getRequestURI()); 

		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}
	
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
	}
	
	@ExceptionHandler(AuthorizationException.class) 
	public ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest request){
		
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.FORBIDDEN.value(), "Access denied", e.getMessage(), request.getRequestURI()); 
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}
	
	@ExceptionHandler(FileException.class) 
	public ResponseEntity<StandardError> file(FileException e, HttpServletRequest request){
		
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "File Error", e.getMessage(), request.getRequestURI()); 
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(AmazonServiceException.class) 
	public ResponseEntity<StandardError> amazonService(AmazonServiceException e, HttpServletRequest request){
		
		HttpStatus code = HttpStatus.valueOf(e.getErrorCode());  // return error code from AmazonServiceException e
		StandardError err = new StandardError(System.currentTimeMillis(), code.value(), "Amazon Service Error", e.getMessage(), request.getRequestURI()); 
		return ResponseEntity.status(code).body(err);
	}
	
	@ExceptionHandler(AmazonClientException.class) 
	public ResponseEntity<StandardError> amazonClient(AmazonClientException e, HttpServletRequest request){
		
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Amazon Client Error", e.getMessage(), request.getRequestURI()); 
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(AmazonS3Exception.class) 
	public ResponseEntity<StandardError> amazonS3(AmazonS3Exception e, HttpServletRequest request){
		
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "AmazonS3 Error", e.getMessage(), request.getRequestURI()); 
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
}
