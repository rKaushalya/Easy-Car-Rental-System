package lk.ijse.carRentalSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDTO {
    private String cusId;
    private String cusName;
    private String cusEmail;
    private String cusPassword;
    private String cusAddress;
    private String cusContact;
    private String cusNIC;
}
