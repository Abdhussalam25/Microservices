package com.salambasha.rest.webservices.Exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//Restcontroller at Exception

@ControllerAdvice // When we have multiple controller, this one is helps us to communicate between each other and
                            //handling the cross cutting concerns.
@RestController
public class CudtomizedResponseEntityExceptionHandler
extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {

		CustomizingExceptionResponse exceptionResponse = new CustomizingExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		
		 return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) throws Exception {

		CustomizingExceptionResponse exceptionResponse = new CustomizingExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		
		 return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		//CustomizingExceptionResponse exceptionResponse = new CustomizingExceptionResponse(new Date(), ex.getMessage(), ex.getBindingResult().toString());
		
		CustomizingExceptionResponse exceptionResponse = new CustomizingExceptionResponse(new Date(), "validation error occured", ex.getBindingResult().getAllErrors().toString());
		
		 return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	
	
	

}
