package com.rammy.project.uber.uberApp.strategies.Impl;

import com.rammy.project.uber.uberApp.entities.RideRequest;
import com.rammy.project.uber.uberApp.services.DistanceService;
import com.rammy.project.uber.uberApp.strategies.RideFareCalculation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideFareSurgePricingCalculationStrategy implements RideFareCalculation {
    private final DistanceService distanceService;
    private static final double surgeFactor = 2;

    @Override
    public double calculateFare(RideRequest rideRequest) {
        double distance = distanceService.calculateDistance(rideRequest.getPickupLocation(), rideRequest.getDropLocation());
        return distance * RIDE_FARE_MULTIPLIER * surgeFactor;
    }
}
