package com.rammy.project.uber.uberApp.dto;
import com.rammy.project.uber.uberApp.entities.enums.PaymentMethod;
import com.rammy.project.uber.uberApp.entities.enums.RideStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideDto {
    private Long id;
    private PointDTO pickupLocation;
    private PointDTO dropLocation;
    private LocalDateTime createdTime;
    private RiderDto rider;
    private DriverDto driver;
    private PaymentMethod paymentMethod;
    private RideStatus rideStatus;
    private Double fare;
    private String otp;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
}
