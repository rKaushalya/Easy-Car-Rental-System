package lk.ijse.carRentalSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehicleShowDTO {
    private String registerNo;
    private String brand;
    private String type;
    private String fuelType;
    private String transmissionType;
    private double dailyRate;
    private double monthlyRate;
    private int freeMileage;
    private double freePrice;
    private double priceForExtraKM;
    private String color;
    private String state;

    private byte[] frontView;
}
