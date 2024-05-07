package com.bikkadit.blog.exceptions;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bikkadit.blog.payloads.ApiResponse;

@RestControllerAdvice
public class GlobleExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class) // whenever this ResourceNotFoundException will occur this
														// controller will  call
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionhandler(ResourceNotFoundException e) {
		String message = e.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodAreNotValidException(MethodArgumentNotValidException e) {

		Map<String, String> reponses = new HashMap<>();

		e.getBindingResult().getAllErrors().forEach((error) -> {
             String fieldName = ((FieldError)error).getField();
             String message = error.getDefaultMessage();
             reponses.put(fieldName, message);
		});

		return new ResponseEntity<Map<String, String>>(reponses, HttpStatus.BAD_REQUEST);
	}

	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<String>  handleDataContraintsVoilationException(SQLIntegrityConstraintViolationException e){
		
		return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data contraints voilation "+e.getMessage());
		
	}
}
