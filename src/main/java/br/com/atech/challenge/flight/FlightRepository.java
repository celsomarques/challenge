package br.com.atech.challenge.flight;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(excerptProjection = FlightProjection.class)
public interface FlightRepository extends CrudRepository<Flight, String> {

	static final String SEARCH = "select flight "
			+ "from Flight flight "
			+ "inner join fetch flight.departure "
			+ "inner join fetch flight.arrival "
			+ "inner join fetch flight.pilot "
			+ "where flight.status = :q "
			+ "order by flight.departureTime, flight.scheduledTime";
	
	@Query(SEARCH)
	@RestResource(path = "status", rel = "status")
	public List<Flight> findByStatus(@Param("q") Status status);
}