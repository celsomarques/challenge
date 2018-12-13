package br.com.atech.challenge.pilot;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import br.com.atech.challenge.flight.Flight;

@Entity
@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@RequiredArgsConstructor
public class Pilot {

	@Id
	@NonNull
	private Long id;

	@NonNull
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pilot")
	private List<Flight> flights;
}