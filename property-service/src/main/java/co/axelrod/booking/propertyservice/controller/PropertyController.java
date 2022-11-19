package co.axelrod.booking.propertyservice.controller;

import co.axelrod.booking.propertyservice.model.Property;
import co.axelrod.booking.propertyservice.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/property")
@RequiredArgsConstructor
public class PropertyController {
    private final PropertyRepository propertyRepository;

    @GetMapping("/{id}")
    public Mono<Property> getProperty(@PathVariable Long id) {
        return propertyRepository.findById(id);
    }

    @PostMapping
    public Mono<Property> createProperty(Property property) {
        return propertyRepository.save(property);
    }
}
