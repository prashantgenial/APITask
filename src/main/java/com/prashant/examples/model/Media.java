package com.prashant.examples.model;

import com.prashant.examples.enums.MyMediaType;

public class Media {

	private String title ;
	private String author;
	private MyMediaType mediaType ;
	
	public Media() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Media(String title, String author, MyMediaType mediaType) {
		super();
		this.title = title;
		this.author = author;
		this.mediaType = mediaType;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public MyMediaType getMediaType() {
		return mediaType;
	}
	public void setMediaType(MyMediaType mediaType) {
		this.mediaType = mediaType;
	}
	@Override
	public String toString() {
		return "Media [title=" + title + ", author=" + author + ", mediaType=" + mediaType + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((mediaType == null) ? 0 : mediaType.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Media other = (Media) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (mediaType != other.mediaType)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
	
}
