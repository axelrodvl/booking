package co.axelrod.bookingservice.service;

import co.axelrod.bookingservice.service.model.Booking;
import co.axelrod.bookingservice.state.Events;
import co.axelrod.bookingservice.state.States;
import lombok.RequiredArgsConstructor;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Component;


import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class BookingService {
    public static final String CONTEXT = "booking";

    private final StateMachineFactory<States, Events> stateMachineFactory;
    private final Map<UUID, StateMachine<States, Events>> stateMachines = new ConcurrentHashMap<>();

    public StateMachine<States, Events> getBooking(UUID instanceId) { return stateMachines.get(instanceId);
    }

    public UUID createBooking(Booking booking) {
        StateMachine<States, Events> stateMachine = stateMachineFactory.getStateMachine();
        stateMachine.getExtendedState().getVariables().put(CONTEXT, booking);
        stateMachine.start();
        UUID instanceId = UUID.randomUUID();
        stateMachines.put(instanceId, stateMachine);
        return instanceId;
    }

    public States confirmBooking(UUID instanceId) {
        StateMachine<States, Events> stateMachine = stateMachines.get(instanceId);
        stateMachine.sendEvent(Events.CONFIRM_BOOKING);
        return stateMachine.getState().getId();
    }

    public States handlePaymentSuccess(UUID instanceId) {
        StateMachine<States, Events> stateMachine = stateMachines.get(instanceId);
        stateMachine.sendEvent(Events.PAY_BY_CUSTOMER);
        return stateMachine.getState().getId();
    }

    public States startBooking(UUID instanceId) {
        StateMachine<States, Events> stateMachine = stateMachines.get(instanceId);
        stateMachine.sendEvent(Events.START_BOOKING);
        return stateMachine.getState().getId();
    }

    public States completeBooking(UUID instanceId) {
        StateMachine<States, Events> stateMachine = stateMachines.get(instanceId);
        stateMachine.sendEvent(Events.COMPLETE_BOOKING);
        return stateMachine.getState().getId();
    }

    public States handlePaymentFailure(UUID instanceId) {
        StateMachine<States, Events> stateMachine = stateMachines.get(instanceId);
        stateMachine.sendEvent(Events.PAYMENT_IS_FAILURE);
        return stateMachine.getState().getId();
    }

    public States handlePaymentSuccessAfterFailure(UUID instanceId) {
        StateMachine<States, Events> stateMachine = stateMachines.get(instanceId);
        stateMachine.sendEvent(Events.PAY_BY_CUSTOMER_AFTER_FAILURE);
        return stateMachine.getState().getId();
    }

    public States cancelBookingAfterPaymentFailure(UUID instanceId) {
        StateMachine<States, Events> stateMachine = stateMachines.get(instanceId);
        stateMachine.sendEvent(Events.CANCEL_BY_PAYMENT_FAILURE);
        return stateMachine.getState().getId();
    }

    public States cancelBookingByCustomer(UUID instanceId) {
        StateMachine<States, Events> stateMachine = stateMachines.get(instanceId);
        stateMachine.sendEvent(Events.CANCEL_BY_CUSTOMER);
        return stateMachine.getState().getId();
    }
    public States declineBookingByHost(UUID instanceId) {
        StateMachine<States, Events> stateMachine = stateMachines.get(instanceId);
        stateMachine.sendEvent(Events.DECLINE_BY_HOST);
        return stateMachine.getState().getId();
    }
}
