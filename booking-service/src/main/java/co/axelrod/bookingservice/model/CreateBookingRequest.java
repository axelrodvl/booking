package co.axelrod.bookingservice.model;

import lombok.Data;

@Data
public class CreateBookingRequest {
    private String guestId;
    private String propertyId;
    private Long timestampFrom;
    private Long timestampTo;
}
