package co.axelrod.bookingservice.config;

import co.axelrod.bookingservice.state.Events;
import co.axelrod.bookingservice.state.States;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

@Configuration
@EnableStateMachine
public class StateMachineConfig
        extends EnumStateMachineConfigurerAdapter<States, Events> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config)
            throws Exception {
        config
                .withConfiguration()
                .autoStartup(true)
                .listener(listener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states)
            throws Exception {
        states
                .withStates()
                .initial(States.CREATED)
                .states(EnumSet.allOf(States.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(States.CREATED).target(States.CANCELED).event(Events.CANCEL_BY_CUSTOMER)
                .and()
                .withExternal()
                .source(States.CREATED).target(States.CONFIRMED).event(Events.CONFIRM_BOOKING)
                .and()
                .withExternal()
                .source(States.CREATED).target(States.DECLINED).event(Events.DECLINE_BY_HOST)
                .and()
                .withExternal()
                .source(States.CONFIRMED).target(States.PAID).event(Events.PAY_BY_CUSTOMER)
                .and()
                .withExternal()
                .source(States.CONFIRMED).target(States.PAYMENT_FAILURE).event(Events.PAY_BY_CUSTOMER)
                .and()
                .withExternal()
                .source(States.PAYMENT_FAILURE).target(States.CANCELED).event(Events.CANCEL_BY_PAYMENT_FAILURE)
                .and()
                .withExternal()
                .source(States.PAYMENT_FAILURE).target(States.PAID).event(Events.PAY_BY_CUSTOMER_AFTER_FAILURE)
                .and()
                .withExternal()
                .source(States.PAID).target(States.STARTED).event(Events.START_BOOKING)
                .and()
                .withExternal()
                .source(States.PAID).target(States.COMPLETED).event(Events.COMPLETE_BOOKING);


    }

    @Bean
    public StateMachineListener<States, Events> listener() {
        return new StateMachineListenerAdapter<States, Events>() {
            @Override
            public void stateChanged(State<States, Events> from, State<States, Events> to) {
                System.out.println("State change to " + to.getId());
            }
        };
    }
}