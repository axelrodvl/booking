package co.axelrod.bookingservice.model;

import lombok.Data;

import java.util.UUID;

@Data
public class ConfirmBookingRequest {
    private UUID bookingId;
}
