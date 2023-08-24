package co.axelrod.bookingservice.service.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class Booking {
    @NotNull
    private String guestId;

    @NotNull
    private String propertyId;

    @NotNull
    @Future
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Long timestampFrom;

    @NotNull
    @Future
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Long timestampTo;
}
