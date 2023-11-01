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
    private double dailyRate;
    private double monthlyRate;
    private int freeMileage;
    private double priceForExtraKM;
    private String transmissionType;

    private String frontView;
    private String backView;
    private String sideView;
    private String interiorView;
}
