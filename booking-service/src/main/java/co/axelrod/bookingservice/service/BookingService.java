package co.axelrod.bookingservice.service;

import co.axelrod.bookingservice.service.model.Booking;
import co.axelrod.bookingservice.state.Events;
import co.axelrod.bookingservice.state.States;
import lombok.RequiredArgsConstructor;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Component;


import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BookingService {
    private final StateMachineFactory<States, Events> stateMachineFactory;

    public UUID createBooking(Booking booking) {
        StateMachine<States, Events> stateMachine = stateMachineFactory.getStateMachine();
        stateMachine.getExtendedState().getVariables().put("booking", booking);
        stateMachine.startReactively();
        return UUID.randomUUID();
    }

    public void confirmBooking(UUID instanceId) {
        StateMachine<States, Events> stateMachine = stateMachineFactory.getStateMachine();
        stateMachine.sendEvent(Events.CONFIRM_BOOKING);
    }
}
