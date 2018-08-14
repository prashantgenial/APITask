package com.prashant.examples.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.prashant.examples.books.Book;

@RestController
public class Media {

	private static final Logger LOGGER = LoggerFactory.getLogger(Media.class);
	
	@RequestMapping(method=RequestMethod.GET,value="/media")
	public Book getMedia1(@RequestParam("input") String input) {
		RestTemplate restTemplate = new RestTemplate();
        Book book = restTemplate.getForObject("https://www.googleapis.com/books/v1/volumes?q="+input, Book.class);
        return book;
	}
	
}

