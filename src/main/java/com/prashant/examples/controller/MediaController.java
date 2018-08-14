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

import com.prashant.examples.APIExceptionDetaile;
import com.prashant.examples.APINotFoundException;
import com.prashant.examples.books.Book;
import com.prashant.examples.client.itunes.Track;

@RestController
public class MediaController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MediaController.class);
	
	@Value("${books.record.count}")
	private Integer records;
	
	@RequestMapping(method=RequestMethod.GET,value="/media")
	public Book getMedia1( @RequestParam(value="input") String input) throws APINotFoundException {
		
		if (StringUtils.isEmpty(input)) 
		      throw new APINotFoundException("Please Enter valid input"+" "+"input"+"--"+input+" "+"not present");
		
		RestTemplate restTemplate = new RestTemplate();
		Book book = restTemplate.getForObject("https://www.googleapis.com/books/v1/volumes?q="+input+"&maxResults="+records, Book.class); 
        return book;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/track")
	public Track getTracks( @RequestParam(value="input") String input) throws APINotFoundException {
		
		if (StringUtils.isEmpty(input)) 
		      throw new APINotFoundException("Please Enter valid input"+" "+"input"+"--"+input+" "+"not present");
		
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://itunes.apple.com/search?term="+input+"&limit="+records;
		
		 List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();        
        //Add the Jackson Message converter
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		// Note: here we are making this converter to process any kind of response, 
		// not only application/*json, which is the default behaviour
		converter.setSupportedMediaTypes(Arrays.asList(MediaType.ALL));
		
		messageConverters.add(converter);  
		restTemplate.setMessageConverters(messageConverters);  
		
		/*HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

		ResponseEntity<Track> track = restTemplate.exchange(url, HttpMethod.POST, entity, Track.class);*/
		Track track = restTemplate.getForObject(url, Track.class);
		LOGGER.info(track.toString());
        return track;
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

