package co.axelrod.booking.propertyservice.repository;

import co.axelrod.booking.propertyservice.model.Property;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PropertyRepository extends ReactiveCrudRepository<Property, Long> {
}