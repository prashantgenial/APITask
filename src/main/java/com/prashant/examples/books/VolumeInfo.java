package com.prashant.examples.books;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VolumeInfo {

	@JsonProperty("title")
	private String title;
	
	@JsonProperty("authors")
	private List<String> authors = null;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<String> getAuthors() {
		return authors;
	}
	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}
	@Override
	public String toString() {
		return "VolumeInfo [title=" + title + ", authors=" + authors + "]";
	}
	
	
	
	
}
