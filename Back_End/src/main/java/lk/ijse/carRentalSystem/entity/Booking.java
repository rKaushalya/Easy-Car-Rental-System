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
public class Booking {
    @Id
    private String bookingId;
    private Date bookingDate;
    private Time bookingTime;
    private double lossDamagePrice;
    private String state;
    private String onlineOrPhysical;

    @OneToOne
    private Customer customer;
}
