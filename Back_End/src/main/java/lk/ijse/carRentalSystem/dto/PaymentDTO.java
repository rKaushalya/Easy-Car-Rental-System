package lk.ijse.carRentalSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentDTO {
    private String paymentId;
    private String bookingId;
    private double damagePrice;
    private double extraKMPrice;
    private double forTheCar;
    private double lateFee;
    private String driverStatus;
}
