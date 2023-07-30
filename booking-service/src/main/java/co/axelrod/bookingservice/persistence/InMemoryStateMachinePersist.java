package co.axelrod.bookingservice.persistence;

import co.axelrod.bookingservice.state.Events;
import co.axelrod.bookingservice.state.States;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;

import java.util.HashMap;
import java.util.Map;

public class InMemoryStateMachinePersist implements StateMachinePersist<States, Events, String> {

    private final Map<String, StateMachineContext<States, Events>> storage = new HashMap<>();

    @Override
    public void write(StateMachineContext<States, Events> context, String contextObj) throws Exception {
        storage.put(contextObj, context);
    }

    @Override
    public StateMachineContext<States, Events> read(String contextObj) throws Exception {
        return storage.get(contextObj);
    }
}

