package com.prashant.examples.client.itunes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {

	@JsonProperty("artistName")
	private String artistName;
	
	@JsonProperty("trackName")
	private String trackName;
	
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public String getTrackName() {
		return trackName;
	}
	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}
	@Override
	public String toString() {
		return "Result [artistName=" + artistName + ", trackName=" + trackName + "]";
	}

	
	
}
