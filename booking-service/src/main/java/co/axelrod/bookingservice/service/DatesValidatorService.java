package co.axelrod.bookingservice.service;
import co.axelrod.bookingservice.service.model.ValidDates;
import co.axelrod.bookingservice.service.model.Booking;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DatesValidatorService implements ConstraintValidator<ValidDates, Booking> {

    @Override
    public boolean isValid(Booking booking, ConstraintValidatorContext context) {
        LocalDateTime startDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(booking.getTimestampFrom()), ZoneId.systemDefault());
        LocalDateTime endDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(booking.getTimestampTo()), ZoneId.systemDefault());

        return startDate.isBefore(endDate);
    }
}
