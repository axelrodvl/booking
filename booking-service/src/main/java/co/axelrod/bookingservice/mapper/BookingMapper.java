package co.axelrod.bookingservice.mapper;

import co.axelrod.bookingservice.model.CreateBookingRequest;
import co.axelrod.bookingservice.service.model.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    Booking map(CreateBookingRequest createBookingRequest);
}
