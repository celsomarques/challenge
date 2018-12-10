package br.com.atech.challenge.airport;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AirportRepository extends CrudRepository<Airport, String> {

}