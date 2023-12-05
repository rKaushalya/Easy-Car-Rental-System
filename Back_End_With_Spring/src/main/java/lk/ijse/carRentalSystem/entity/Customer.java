package lk.ijse.carRentalSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CustomerDetails customerDetails;
}
