package lk.ijse.carRentalSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Vehicle {
    @Id
    private String registerNo;
    private String brand;
    private String type;
    private String transmissionType;
    private double dailyRate;
    private double monthlyRate;
    private int freeMileage;
    private double freePrice;
    private double priceForExtraKM;
    private String color;
    private String state;
}
