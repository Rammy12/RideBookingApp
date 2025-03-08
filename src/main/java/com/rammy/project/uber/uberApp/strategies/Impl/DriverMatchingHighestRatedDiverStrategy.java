package com.rammy.project.uber.uberApp.strategies.Impl;

import com.rammy.project.uber.uberApp.dto.RideRequestDto;
import com.rammy.project.uber.uberApp.entities.Driver;
import com.rammy.project.uber.uberApp.entities.RideRequest;
import com.rammy.project.uber.uberApp.repositories.DriverRepository;
import com.rammy.project.uber.uberApp.strategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverMatchingHighestRatedDiverStrategy implements DriverMatchingStrategy {

    private final DriverRepository driverRepository;
    @Override
    public List<Driver> findMatchingDriver(RideRequest rideRequest) {
        return driverRepository.findTenNearbyTopRatedDrivers(rideRequest.getPickupLocation());
    }
}
