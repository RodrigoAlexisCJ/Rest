package com.rod.restservice.RestService.exception;

import java.net.http.HttpHeaders;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rod.restservice.RestService.model.UserNotFoundException;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetail> handleAllException(Exception ex, 
			WebRequest request) {
		ErrorDetail errorDetail = new ErrorDetail(LocalDateTime.now(), ex.getMessage(), 
				request.getDescription(false));
		return new ResponseEntity<>(errorDetail,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorDetail> handleUserNotFoundException(Exception ex,
			WebRequest request) {
		ErrorDetail errorDetail = new ErrorDetail(LocalDateTime.now(), ex.getMessage(), 
				request.getDescription(false));
		return new ResponseEntity<>(errorDetail,HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, org.springframework.http.HttpHeaders headers, HttpStatus status,WebRequest request){
		ErrorDetail errorDetails = new ErrorDetail(LocalDateTime.now(),ex.getFieldError().getDefaultMessage(),request.getDescription(false));
		return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
	}
}
