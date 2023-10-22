package lk.ijse.carRentalSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.sql.Time;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentDetails {
    @Id
    private String paymentId;
    private Date paymentDate;
    private Time paymentTime;
    private double damagePrice;
    private double extraKMPrice;
    private double forTheCar;
    private double lateFee;

    @OneToOne
    private Booking booking;
}
