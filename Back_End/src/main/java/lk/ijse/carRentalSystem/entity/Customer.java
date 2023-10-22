package lk.ijse.carRentalSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer {
    @Id
    private String cId;
    private String name;
    private String email;
    private String password;
    @Column(columnDefinition = "TEXT")
    private String address;
    private String contactNo;

    @OneToOne
    private CustomerDetails customerDetails;
}
