package com.prashant.examples.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.prashant.examples.MediaDownloader;
import com.prashant.examples.client.books.Book;
import com.prashant.examples.client.itunes.Track;
import com.prashant.examples.exception.APIExceptionDetaile;
import com.prashant.examples.exception.APINotFoundException;
import com.prashant.examples.model.Media;

@RestController
public class MediaController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MediaController.class);
	
	@Value("${books.record.count}")
	private Integer records;
	
	@RequestMapping(method=RequestMethod.GET,value="/media1")
	public List<Media> getMedia( @RequestParam(value="input") String input) throws APINotFoundException {
		
		if (StringUtils.isEmpty(input)) 
		      throw new APINotFoundException("Please Enter valid input"+" "+"input"+"--"+input+" "+"not present");
		
		MediaDownloader md = new MediaDownloader();
		return md.go(input,records);		
		
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

