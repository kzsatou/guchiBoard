package com.example.guchiBoard.entity;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Weather {
	
	private String publicTime;

	private String publicTimeFormatted;
	
	private String publishingOffice;
	
	@JsonGetter("publishingOffice")
	public String getpublishingOffice() {
	    return publishingOffice;
	  }
	
	private String title;
	
	private String link;

	private Map<String, String> description = new HashMap<>();
	
	private Map<String, String> forecasts = new HashMap<>();
	
	private Map<String, String> location = new HashMap<>();
	
	private Map<String, String> copyright = new HashMap<>();
}
