package lk.ijse.carRentalSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehicleDetails {
    @Id
    private String fileName;
    private String filePath;
    private String fileType;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Vehicle vehicle;
}
