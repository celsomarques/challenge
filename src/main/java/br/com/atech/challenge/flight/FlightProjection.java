package br.com.atech.challenge.flight;

import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

import br.com.atech.challenge.airport.AirportProjection;
import br.com.atech.challenge.pilot.Pilot;

@Projection(name = "flight", types = { Flight.class })
public interface FlightProjection {

	Long getId();
	
	Date getDepartureTime();
	
	Date getScheduledTime();
	
	String getAircraft();
	
	AirportProjection getDeparture();
	
	AirportProjection getArrival();
	
	Pilot getPilot();
}