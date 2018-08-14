package com.prashant.examples.books;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Volumes implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3192577851722924542L;
	@JsonProperty("id")
	private String id ;
	
	@JsonProperty("publisher")
	private String publisher;
	
	@JsonProperty("description")
	private String description ;
	public Volumes() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Volumes(String id, String publisher, String description) {
		super();
		this.id = id;
		this.publisher = publisher;
		this.description = description;
	}



	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}



	@Override
	public String toString() {
		return "Volumes [id=" + id + ", publisher=" + publisher + ", description=" + description + "]";
	}

	
	
	
}
