package co.axelrod.bookingservice.service;

import co.axelrod.bookingservice.service.model.Booking;
import co.axelrod.bookingservice.state.States;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookingServiceTest {
    @Autowired
    BookingService bookingService;

    Booking firstBooking = Booking.builder()
            .guestId("G1")
            .propertyId("P1")
            .timestampFrom(System.currentTimeMillis() - 1000)
            .timestampTo(System.currentTimeMillis())
            .build();

    Booking secondBooking = Booking.builder()
            .guestId("G2")
            .propertyId("P2")
            .timestampFrom(System.currentTimeMillis() - 1000)
            .timestampTo(System.currentTimeMillis())
            .build();

    @Test
    void createBookingTest() {
        UUID firstStateMachineUUID = bookingService.createBooking(firstBooking);
        UUID secondStateMachineUUID = bookingService.createBooking(secondBooking);
        assertNotNull(firstStateMachineUUID);
        assertNotNull(secondStateMachineUUID);
        assertFalse(firstStateMachineUUID.equals(secondStateMachineUUID));

        var firstStateMachine = bookingService.getBooking(firstStateMachineUUID);
        assertEquals("P1", firstStateMachine.getExtendedState().get(BookingService.CONTEXT, Booking.class).getPropertyId());
        var secondStateMachine = bookingService.getBooking(secondStateMachineUUID);
        assertEquals("P2", secondStateMachine.getExtendedState().get(BookingService.CONTEXT, Booking.class).getPropertyId());
    }

    @Test
    void confirmBookingTest() {
        UUID stateMachineUUID = bookingService.createBooking(firstBooking);
        var stateMachine = bookingService.getBooking(stateMachineUUID);

        bookingService.confirmBooking(stateMachineUUID);
        assertEquals(States.CONFIRMED, stateMachine.getState().getId());
    }
}