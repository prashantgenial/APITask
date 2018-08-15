package com.prashant.examples.controller;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.prashant.examples.exception.APIExceptionDetaile;
import com.prashant.examples.exception.APINotFoundException;


@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


  @ExceptionHandler(APINotFoundException.class)
  public final ResponseEntity<APIExceptionDetaile> handleUserNotFoundException(APINotFoundException ex, WebRequest request) {
	  APIExceptionDetaile errorDetails = new APIExceptionDetaile(new Date(), ex.getMessage(),
        request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<APIExceptionDetaile> handleAllExceptions(Exception ex, WebRequest request) {
	 
	  APIExceptionDetaile errorDetails = new APIExceptionDetaile(new Date(), ex.getMessage(),
        request.getDescription(false));
    errorDetails.setMessage("Wrong input please use right input");
    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }


}