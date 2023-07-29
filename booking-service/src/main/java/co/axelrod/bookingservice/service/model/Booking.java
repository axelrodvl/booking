package co.axelrod.bookingservice.service.model;

import lombok.Data;

@Data
public class Booking {
    private String guestId;
    private String propertyId;
    private Long timestampFrom;
    private Long timestampTo;
}
