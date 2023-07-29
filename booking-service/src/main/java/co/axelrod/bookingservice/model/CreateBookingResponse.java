package co.axelrod.bookingservice.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CreateBookingResponse {
    private UUID bookingId;
    String status;
}
