package lk.ijse.carRentalSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DriverScheduleDTO {
    private Date bookingDate;
    private String cusName;
    private String cusContact;
    private Date pickupDate;
    private String carName;
}
