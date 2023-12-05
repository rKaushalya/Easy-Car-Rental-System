package lk.ijse.carRentalSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehicleDetails {
    @Id
    private String fileName;
    private String filePath;
    private String fileType;

    @ManyToOne()
    private Vehicle vehicle;
}
