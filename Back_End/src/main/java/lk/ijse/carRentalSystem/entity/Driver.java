package lk.ijse.carRentalSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Driver {
    @Id
    private String driverId;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String address;
    @Column(name = "date_of_birth")
    private Date dob;
    private String city;

    @OneToOne
    private DriverDetails driverDetails;
}
