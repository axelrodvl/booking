package co.axelrod.bookingservice.service.model;
import co.axelrod.bookingservice.service.DatesValidatorService;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DatesValidatorService.class)
public @interface ValidDates {
    String message() default "Starting date should be less than the end date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
