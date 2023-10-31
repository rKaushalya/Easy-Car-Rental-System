package lk.ijse.carRentalSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Booking {
    @Id
    private String bookingId;
    @CreationTimestamp
    private Date bookingDate;
    @CreationTimestamp
    private Time bookingTime;
    private String bookingLocation;
    private double lossDamagePrice;
    private String state;
    private String onlineOrPhysical;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private BookingSlip bookingSlip;
}
