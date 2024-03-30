package com.example.guchiBoard.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("polly")
public class AWSConfig {

	@Bean
	public Region awsRegion() {
		return Region.getRegion(Regions.AP_NORTHEAST_1);
	}

	/*
	 * private String ACCESS_KEY_ID;
	 * 
	 * public String getACCESS_KEY_ID() { return ACCESS_KEY_ID; } public void
	 * setACCESS_KEY_ID(String ACCESS_KEY_ID) { this.ACCESS_KEY_ID = ACCESS_KEY_ID;
	 * }
	 * 
	 * private String SECRET_ACCESS_KEY;
	 * 
	 * public String getSECRET_ACCESS_KEY() { return SECRET_ACCESS_KEY; }
	 * 
	 * public void setSECRET_ACCESS_KEY(String SECRET_ACCESS_KEY) {
	 * this.SECRET_ACCESS_KEY = SECRET_ACCESS_KEY; }
	 */
}
