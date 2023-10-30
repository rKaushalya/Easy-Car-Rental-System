package lk.ijse.carRentalSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Vehicle {
    @Id
    private String registerNo;
    private String brand;
    private String type;
    private String fuelType;
    private String transmissionType;
    private double dailyRate;
    private double monthlyRate;
    private int noOfPassenger;
    private int freeMileage;
    private double freePrice;
    private double priceForExtraKM;
    private String color;
    private String state;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    List<VehicleDetails> vehicleDetails = new ArrayList<>();
}
