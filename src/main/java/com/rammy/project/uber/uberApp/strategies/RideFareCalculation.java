package com.rammy.project.uber.uberApp.strategies;


import com.rammy.project.uber.uberApp.entities.RideRequest;

public interface RideFareCalculation {
    double RIDE_FARE_MULTIPLIER=10;
    double calculateFare(RideRequest rideRequest);
}
