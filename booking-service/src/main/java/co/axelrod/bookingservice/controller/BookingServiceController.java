package co.axelrod.bookingservice.controller;

import co.axelrod.bookingservice.mapper.BookingMapper;
import co.axelrod.bookingservice.model.CreateBookingRequest;
import co.axelrod.bookingservice.model.CreateBookingResponse;
import co.axelrod.bookingservice.service.BookingService;
import co.axelrod.bookingservice.service.model.Booking;
import co.axelrod.bookingservice.state.States;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController("/api/booking")
@RequiredArgsConstructor
public class BookingServiceController {
    private final BookingService service;
    private final BookingMapper mapper;

    @PostMapping("/create")
    ResponseEntity<CreateBookingResponse> createBooking(@Valid @RequestBody CreateBookingRequest createBookingRequest,
                                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        Booking booking = mapper.map(createBookingRequest);
        UUID bookingId = service.createBooking(booking);
        return ResponseEntity.ok(
                CreateBookingResponse.builder()
                        .bookingId(bookingId)
                        .build()
        );
    }
}
