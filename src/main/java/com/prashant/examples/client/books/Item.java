package com.prashant.examples.client.books;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3192577851722924542L;
	@JsonProperty("id")
	private String id ;
	
	@JsonProperty("volumeInfo")
	private VolumeInfo volumeInfo;
	
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Item(String id, VolumeInfo volumeInfo) {
		super();
		this.id = id;
		this.volumeInfo = volumeInfo;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}


	public VolumeInfo getVolumeInfo() {
		return volumeInfo;
	}


	public void setVolumeInfo(VolumeInfo volumeInfo) {
		this.volumeInfo = volumeInfo;
	}


	@Override
	public String toString() {
		return "Volumes [id=" + id + ", volumeInfo=" + volumeInfo + "]";
	}

	
	
	
}
