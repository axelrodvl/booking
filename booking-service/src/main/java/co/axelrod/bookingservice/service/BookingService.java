package co.axelrod.bookingservice.service;

import co.axelrod.bookingservice.service.model.Booking;
import co.axelrod.bookingservice.state.Events;
import co.axelrod.bookingservice.state.States;
import lombok.RequiredArgsConstructor;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingService {
    private final StateMachine<States, Events> stateMachine;

    public States createBooking(Booking booking) {
        stateMachine.sendEvent(Events.CANCEL_BY_CUSTOMER);
        return States.CREATED; // TODO
    }

    public States confirmBooking(Booking booking) {
        stateMachine.sendEvent(Events.CONFIRM_BOOKING);
        return States.CONFIRMED;
    }

    public boolean isValidBookingDates(Booking booking) {
        return booking.getTimestampFrom() < booking.getTimestampTo();
    }
}
