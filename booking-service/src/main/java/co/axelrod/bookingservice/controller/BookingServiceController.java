package co.axelrod.bookingservice.controller;

import co.axelrod.bookingservice.mapper.BookingMapper;
import co.axelrod.bookingservice.model.CreateBookingRequest;
import co.axelrod.bookingservice.model.CreateBookingResponse;
import co.axelrod.bookingservice.service.BookingService;
import co.axelrod.bookingservice.service.model.Booking;
import co.axelrod.bookingservice.state.States;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/booking")
@RequiredArgsConstructor
public class BookingServiceController {
    private final BookingService service;
    private final BookingMapper mapper;

    @PostMapping("/create")
    ResponseEntity<CreateBookingResponse> createBooking(CreateBookingRequest createBookingRequest) {
        Booking booking = mapper.map(createBookingRequest);
        States state = service.createBooking(booking);
        return ResponseEntity.ok(
                CreateBookingResponse.builder()
                        .status(state.toString())
                        .build()
        );
    }
}