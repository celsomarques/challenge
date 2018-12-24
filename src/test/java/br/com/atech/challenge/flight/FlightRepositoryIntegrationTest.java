package br.com.atech.challenge.flight;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightRepositoryIntegrationTest {

	@Autowired
	FlightRepository repository;
	
	@Test
    public void list() {
        Iterable<Flight> flights = this.repository.findAll();
        Assert.assertEquals(2, flights.spliterator().estimateSize());
    }
	
	@Test
    public void findByStatusWhenActiveIsPassed() {
        Iterable<Flight> flights = this.repository.findByStatus(Status.ACTIVE);
        Assert.assertEquals(1, flights.spliterator().estimateSize());
        Flight flight = flights.iterator().next();
        Assert.assertEquals(new Long(1), flight.getId());
    }
	
	@Test
    public void findByStatusWhenLandedIsPassed() {
        Iterable<Flight> flights = this.repository.findByStatus(Status.LANDED);
        Assert.assertEquals(1, flights.spliterator().estimateSize());
        Flight flight = flights.iterator().next();
        Assert.assertEquals(new Long(2), flight.getId());
    }
}