package com.prashant.examples.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Media {

	@RequestMapping(method=RequestMethod.GET,value="/media")
	public String getMedia1(@RequestParam("input") String input) {
		return "Hello "+input ;
	}
}
