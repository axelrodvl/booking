package co.axelrod.bookingservice.service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Booking {
    private String guestId;
    private String propertyId;
    private Long timestampFrom;
    private Long timestampTo;
}
