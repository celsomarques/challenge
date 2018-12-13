package br.com.atech.challenge.pilot;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PilotRepository extends CrudRepository<Pilot, String> {

}