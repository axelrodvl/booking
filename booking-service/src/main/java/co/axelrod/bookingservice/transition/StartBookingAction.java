package co.axelrod.bookingservice.transition;

import co.axelrod.bookingservice.state.Events;
import co.axelrod.bookingservice.state.States;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StartBookingAction implements Action<States, Events> {

    @Override
    public void execute(StateContext<States, Events> context) {
        sendMail("Booking has started");
    }

    private void sendMail(String message) {
        log.info("Notification: {}", message);
    }

}
