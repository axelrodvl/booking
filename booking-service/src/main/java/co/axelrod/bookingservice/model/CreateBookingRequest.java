package co.axelrod.bookingservice.model;
import javax.validation.constraints.FutureOrPresent;

import lombok.Data;

@Data
public class CreateBookingRequest {
    private String guestId;
    private String propertyId;

    @FutureOrPresent(message = "TimestampFrom must be in the future or present")
    private Long timestampFrom;

    @FutureOrPresent(message = "TimestampTo must be in the future or present")
    private Long timestampTo;
}
