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

    public StateMachine<States, Events> getBooking(UUID instanceId) {
        return stateMachines.get(instanceId);
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
}
