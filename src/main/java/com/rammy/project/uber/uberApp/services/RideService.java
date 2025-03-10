package com.rammy.project.uber.uberApp.services;

import com.rammy.project.uber.uberApp.dto.RideRequestDto;
import com.rammy.project.uber.uberApp.entities.Driver;
import com.rammy.project.uber.uberApp.entities.Ride;
import com.rammy.project.uber.uberApp.entities.RideRequest;
import com.rammy.project.uber.uberApp.entities.Rider;
import com.rammy.project.uber.uberApp.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {
    Ride getRideById(Long rideId);

    Ride creatNewRide(RideRequest rideRequest, Driver driver);

    Ride updateRideStatus(Ride ride, RideStatus rideStatus);

    Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest);

    Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest);
}
