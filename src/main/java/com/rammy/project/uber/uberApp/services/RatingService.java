package com.rammy.project.uber.uberApp.services;

import com.rammy.project.uber.uberApp.dto.DriverDto;
import com.rammy.project.uber.uberApp.dto.RiderDto;
import com.rammy.project.uber.uberApp.entities.Ride;


public interface RatingService {

    DriverDto rateDriver(Ride ride, Integer rating);
    RiderDto rateRider(Ride ride, Integer rating);
    void creatingNewRating(Ride ride);
}
