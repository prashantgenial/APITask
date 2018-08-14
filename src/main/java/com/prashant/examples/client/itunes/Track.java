package com.prashant.examples.client.itunes;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Track {
	
	@JsonProperty("resultCount")
	private Integer resultCount;
	@JsonProperty("results")
	private List<Result> results = null;
	
	
	public Integer getResultCount() {
		return resultCount;
	}
	public void setResultCount(Integer resultCount) {
		this.resultCount = resultCount;
	}
	public List<Result> getResults() {
		return results;
	}
	public void setResults(List<Result> results) {
		this.results = results;
	}
	@Override
	public String toString() {
		return "Track [resultCount=" + resultCount + ", results=" + results + "]";
	}
	
	

}
