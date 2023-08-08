package co.axelrod.bookingservice.service;

import co.axelrod.bookingservice.mapper.BookingMapper;
import co.axelrod.bookingservice.model.CreateBookingRequest;
import co.axelrod.bookingservice.service.model.Booking;
import co.axelrod.bookingservice.state.States;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookingServiceTest {
    @Autowired
    BookingService bookingService;
    @Autowired
    BookingMapper mapper;

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

    @Test
    @DisplayName("""
            Happy path:
            - Guest creates booking
            - Host confirms
            - Payment is successful in first attempt
            - Guest started booking
            - Guest finished booking
            """)
    void happyPath() {
        CreateBookingRequest request = new CreateBookingRequest();
        Booking booking_from_request = mapper.map(request);
        request.setGuestId("guest123");
        request.setPropertyId("property456");
        request.setTimestampFrom(System.currentTimeMillis());
        request.setTimestampTo(request.getTimestampFrom() + 86400000);

        UUID stateMachineUUID = bookingService.createBooking(booking_from_request);

        bookingService.confirmBooking(stateMachineUUID);
        bookingService.handlePaymentSuccess(stateMachineUUID);
        bookingService.startBooking(stateMachineUUID);
        bookingService.completeBooking(stateMachineUUID);

        var stateMachine = bookingService.getBooking(stateMachineUUID);

        Assertions.assertEquals(States.COMPLETED, stateMachine.getState().getId());

    }

    @Test
    @DisplayName("""
            Happy path with payment issues - :
            - Guest creates booking
            - Host confirms
            - Payment is successful in third attempt
            - Guest started booking
            - Guest finished booking
            """)
    void happyPathWithPaymentIssues() {
        UUID stateMachineUUID = bookingService.createBooking(firstBooking);
        bookingService.confirmBooking(stateMachineUUID);
        bookingService.handlePaymentFailure(stateMachineUUID);
        bookingService.handlePaymentFailure(stateMachineUUID);
        bookingService.handlePaymentSuccessAfterFailure(stateMachineUUID);
        bookingService.startBooking(stateMachineUUID);
        bookingService.completeBooking(stateMachineUUID);

        var stateMachine = bookingService.getBooking(stateMachineUUID);

        Assertions.assertEquals(States.COMPLETED, stateMachine.getState().getId());
    }

    @Test
    @DisplayName("""
            Booking canceled - :
            - Guest creates booking
            - Host confirms
            - Payment is failed
            - Booking canceled
            """)
    void paymentIssuesBookingDeclined() {
        UUID stateMachineUUID = bookingService.createBooking(firstBooking);
        bookingService.confirmBooking(stateMachineUUID);
        bookingService.handlePaymentFailure(stateMachineUUID);
        bookingService.cancelBookingAfterPaymentFailure(stateMachineUUID);

        var stateMachine = bookingService.getBooking(stateMachineUUID);

        Assertions.assertEquals(States.CANCELED, stateMachine.getState().getId());
    }

    @Test
    @DisplayName("""
            Booking canceled - :
            - Guest creates booking
            - Guest cancels booking
            """)
    void customerSelfBookingDeclined() {
        UUID stateMachineUUID = bookingService.createBooking(firstBooking);
        bookingService.cancelBookingByCustomer(stateMachineUUID);

        var stateMachine = bookingService.getBooking(stateMachineUUID);

        Assertions.assertEquals(States.CANCELED, stateMachine.getState().getId());
    }

    @Test
    @DisplayName("""
            Booking declined - :
            - Guest creates booking
            - Host declines booking
            """)
    void hostDeclinedBooking() {
        UUID stateMachineUUID = bookingService.createBooking(firstBooking);
        bookingService.declineBookingByHost(stateMachineUUID);

        var stateMachine = bookingService.getBooking(stateMachineUUID);

        Assertions.assertEquals(States.DECLINED, stateMachine.getState().getId());
    }


}