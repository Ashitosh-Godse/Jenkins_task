package com.epam.advice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.epam.customexception.AssociateException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(AssociateException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ExceptionResponse batchExceptionHandler(AssociateException e,WebRequest r) {
	log.error("Entered into batchExceptionHandler ");
	return new ExceptionResponse(new Date().toString(),
								 HttpStatus.NOT_FOUND.toString(),
								 e.getMessage(),
								 r.getDescription(false));
	
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ExceptionResponse methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e,WebRequest r) {
		log.error("Entered into sqlIntegrityConstraintViolationException ");
		List<String>errorList=new ArrayList<>();
		e.getBindingResult().getAllErrors().forEach(err->errorList.add(err.getDefaultMessage()));
		return new ExceptionResponse(new Date().toString(), HttpStatus.ACCEPTED.toString(),errorList.toString(),r.getDescription(false));
	}

}
