package br.com.atech.challenge.airport;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "airport", types = { Airport.class })
public interface AirportProjection {

	String getCode();
	
	String getCity();
}