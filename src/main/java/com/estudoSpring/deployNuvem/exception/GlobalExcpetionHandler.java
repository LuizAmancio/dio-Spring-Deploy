package com.estudoSpring.deployNuvem.exception;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExcpetionHandler extends ResponseEntityExceptionHandler {
	
	private final Logger logger = LoggerFactory.getLogger(GlobalExcpetionHandler.class);
	
	private HttpHeaders headers() {
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		return header;
	}
	
	private ResponseError responseError(String msg, HttpStatus status) {
		ResponseError response = new ResponseError();
		response.setStatusCode(status.value());
		response.setError(msg);
		return response;
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleGeneral(Exception e, WebRequest request){
		if(e instanceof BusinessException) {
			return handleBusinessException((BusinessException) e, request, HttpStatus.CONFLICT);
		}
		
		if(e instanceof NoSuchElementException) {
			return handleNoSuchElementException((NoSuchElementException) e, request, HttpStatus.NOT_FOUND);
		}
		
		if(e instanceof IllegalArgumentException) {
			return handleIllegalArgumentException((IllegalArgumentException) e, request, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		String message = "Erro interno no servidor. Por favor, tente novamente.";
		logger.error("", e);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
	}
	
	private ResponseEntity<Object> handleBusinessException(BusinessException e, WebRequest request, HttpStatus status){
		ResponseError error = responseError(e.getMessage(), HttpStatus.CONFLICT);
		return handleExceptionInternal(e, error, headers(), HttpStatus.CONFLICT, request);
	}
	
	private ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException e, WebRequest request, HttpStatus status){
		ResponseError error = responseError("Usuário não encontrado", status);
		return handleExceptionInternal(e, error, headers(), status, request);
	}
	private ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e, WebRequest request, HttpStatus status){
		ResponseError error = responseError(e.getMessage(), status);
		return handleExceptionInternal(e, error, headers(), status, request);
	}
}
