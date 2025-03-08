package com.rammy.project.uber.uberApp.dto;


import com.rammy.project.uber.uberApp.entities.enums.PaymentMethod;
import com.rammy.project.uber.uberApp.entities.enums.RideRequestStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RideRequestDto {
    private Long id;
    private PointDTO pickupLocation;
    private PointDTO dropLocation;
    private LocalDateTime requestedTime;
    private RiderDto rider;
    private Double fare;
    private PaymentMethod paymentMethod;
    private RideRequestStatus rideRequestStatus;
}
