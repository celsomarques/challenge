package br.com.atech.challenge.flight;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = FlightProjection.class)
public interface FlightRepository extends CrudRepository<Flight, String> {

}