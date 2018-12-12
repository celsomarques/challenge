package br.com.atech.challenge.flight;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import br.com.atech.challenge.airport.Airport;
import br.com.atech.challenge.pilot.Pilot;

@Entity
@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@RequiredArgsConstructor
public class Flight {

	@Id
	@NonNull
	private Long id;
	
	@NonNull
	@Column(name = "departure_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date departureTime;
	
	@NonNull
	@Column(name = "scheduled_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date scheduledTime;

	@NonNull
	@ManyToOne
	private Airport departure;
	
	@NonNull
	@ManyToOne
	private Airport arrival;
	
	@NonNull
	@ManyToOne
	private Pilot pilot;
}