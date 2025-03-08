package com.rammy.project.uber.uberApp.strategies;


import com.rammy.project.uber.uberApp.entities.Driver;
import com.rammy.project.uber.uberApp.entities.RideRequest;

import java.util.List;

public interface DriverMatchingStrategy {
    List<Driver> findMatchingDriver(RideRequest rideRequest);
}
