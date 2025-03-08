package com.rammy.project.uber.uberApp.entities;

import com.rammy.project.uber.uberApp.entities.enums.PaymentMethod;
import com.rammy.project.uber.uberApp.entities.enums.RideRequestStatus;
import com.rammy.project.uber.uberApp.entities.enums.RideStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(indexes = {
        @Index(name = "idx_ride_rider", columnList = "rider_id"),
        @Index(name = "idx_driver", columnList = "driver_id")
})
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point pickupLocation;

    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point dropLocation;

    @CreationTimestamp
    private LocalDateTime createdTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Rider rider;

    @ManyToOne(fetch = FetchType.LAZY)
    private Driver driver;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private RideStatus rideStatus;

    private Double fare;
    private String otp;

    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
}










