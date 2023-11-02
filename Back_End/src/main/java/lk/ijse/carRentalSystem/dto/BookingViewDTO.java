package lk.ijse.carRentalSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingViewDTO {
    private String customerId;
    private String customerName;
    private String customerEmail;
    private String customerContact;
    private String customerAddress;

    private String registerNo;

    private Date carBookDate;
    private Date pickupDate;
    private String driverState;
    private String state;

}
