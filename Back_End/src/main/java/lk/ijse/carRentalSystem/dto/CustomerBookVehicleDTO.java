package lk.ijse.carRentalSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerBookVehicleDTO {
    private String registerNo;
    private String brand;
    private String type;
    private int noOfPassenger;
    private String color;
    private String state;

    private String frontView;
    private String backView;
    private String sideView;
    private String interiorView;
}
