package com.prashant.examples.books;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Book implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7105388412869839940L;
	
	@JsonProperty("totalItems")
	private Integer totalItems ;
	
	@JsonProperty("items")
	private List<Item> items = null;
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(Integer totalItems, List<Item> items) {
		super();
		this.totalItems = totalItems;
		this.items = items;
	}
	public Integer getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(Integer totalItems) {
		this.totalItems = totalItems;
	}
	
	@JsonProperty("items")
	public List<Item> getItems() {
		return items;
	}
	
	@JsonProperty("items")
	public void setItems(List<Item> items) {
		this.items = items;
	}
	@Override
	public String toString() {
		return "Book [totalItems=" + totalItems + ", items=" + items + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + ((totalItems == null) ? 0 : totalItems.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (totalItems == null) {
			if (other.totalItems != null)
				return false;
		} else if (!totalItems.equals(other.totalItems))
			return false;
		return true;
	}
	

}
