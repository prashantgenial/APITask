package com.prashant.examples.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.prashant.examples.APIExceptionDetaile;
import com.prashant.examples.APINotFoundException;
import com.prashant.examples.books.Book;

@RestController
public class Media {

	private static final Logger LOGGER = LoggerFactory.getLogger(Media.class);
	
	@RequestMapping(method=RequestMethod.GET,value="/media")
	public Book getMedia1( @RequestParam(value="input") String input) throws APINotFoundException {
		
		if (input==null) 
		      throw new APINotFoundException("Please Enter valid input"+" "+"input"+"--"+input+" "+"not present");
		RestTemplate restTemplate = new RestTemplate();
		 Book book = restTemplate.getForObject("https://www.googleapis.com/books/v1/volumes?q="+input, Book.class); 
        return book;
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<APIExceptionDetaile> handleMissingParams(MissingServletRequestParameterException ex) {
	    String name = ex.getParameterName();
	    System.out.println(name + " parameter is missing");
	    APIExceptionDetaile errorDetails = new APIExceptionDetaile(new Date(), ex.getMessage(),
	            ex.getParameterName() + " is required.");
	        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
}

