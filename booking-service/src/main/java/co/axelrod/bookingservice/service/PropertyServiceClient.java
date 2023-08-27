package co.axelrod.bookingservice.service;

import co.axelrod.bookingservice.service.model.Property;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "property-service", url = "${property.service.url}")
public interface PropertyServiceClient {

    @GetMapping("/api/property/{propertyId}")
    Property getPropertyById(@PathVariable Long propertyId);
}
