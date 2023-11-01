package lk.ijse.carRentalSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingDTO {
    private String bId;
    private String cName;
    private String regNo;
    private String location;
    private double lossDamagePrice;
    private String onlineOrPhysical;
    private double carPrice;
    private Date carBookDate;
    private Date pickupDate;
    private String driverState;
}
