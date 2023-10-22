package lk.ijse.carRentalSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingDetails {
    @EmbeddedId
    private BookingDetailsPK bookingDetailsPK;

    @ManyToOne
    @JoinColumn(name = "bookingId", insertable = false, updatable = false)
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "registerNo", insertable = false, updatable = false)
    private Vehicle vehicle;

    @OneToOne
    private Driver driver;

    private Date carBookDate;
    private Date pickupDate;
    private Time pickupTime;
}
