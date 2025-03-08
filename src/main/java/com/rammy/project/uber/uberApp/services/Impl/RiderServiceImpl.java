package com.rammy.project.uber.uberApp.services.Impl;


import com.rammy.project.uber.uberApp.dto.DriverDto;
import com.rammy.project.uber.uberApp.dto.RideDto;
import com.rammy.project.uber.uberApp.dto.RideRequestDto;
import com.rammy.project.uber.uberApp.dto.RiderDto;
import com.rammy.project.uber.uberApp.entities.*;
import com.rammy.project.uber.uberApp.entities.enums.RideRequestStatus;
import com.rammy.project.uber.uberApp.entities.enums.RideStatus;
import com.rammy.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.rammy.project.uber.uberApp.repositories.RideRequestRepository;
import com.rammy.project.uber.uberApp.repositories.RiderRepository;
import com.rammy.project.uber.uberApp.services.DriverService;
import com.rammy.project.uber.uberApp.services.RatingService;
import com.rammy.project.uber.uberApp.services.RideService;
import com.rammy.project.uber.uberApp.services.RiderService;
import com.rammy.project.uber.uberApp.strategies.RideStrategyManager;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RiderServiceImpl implements RiderService {
    private final ModelMapper modelMapper;
    private final RideStrategyManager rideStrategyManager;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;
    private final RideService rideService;
    private final DriverService driverService;
    private final RatingService ratingService;

    @Override
    @Transactional
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        Rider rider = getCurrentRider();
        RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        rideRequest.setRider(rider);
        Double fare = rideStrategyManager.rideFareCalculation().calculateFare(rideRequest);
        rideRequest.setFare(fare);
        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);
        List<Driver> drivers = rideStrategyManager
                .driverMatchingStrategy(getCurrentRider().getRating()).findMatchingDriver(rideRequest);
        // TODO : send notification to all the drivers
        return modelMapper.map(savedRideRequest, RideRequestDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        Rider rider = getCurrentRider();
        Ride ride = rideService.getRideById(rideId);
        if (!rider.equals(ride.getRider())) {
            throw new RuntimeException("Rider is not allowed to cancel this ride :" + rideId);
        }
        if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
            throw new RuntimeException("Ride cannot be cancelled, invalid status :" + RideStatus.CONFIRMED);
        }
        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.CANCELLED);
        driverService.updateDriverAvailability(ride.getDriver(), true);
        return modelMapper.map(savedRide, RideDto.class);
    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rating) {
        Ride ride = rideService.getRideById(rideId);
        Rider rider = getCurrentRider();
        if (!rider.equals(ride.getRider())) {
            throw new RuntimeException("Rider is not the owner of this Ride");
        }
        if (!ride.getRideStatus().equals(RideStatus.ENDED)) {
            throw new RuntimeException("Ride status is not ENDED hence cannot be rating, status" + ride.getRideStatus());
        }
        return ratingService.rateDriver(ride,rating);
    }

    @Override
    public RiderDto getMyProfile() {
        Rider currentRider = getCurrentRider();
        return modelMapper.map(currentRider, RiderDto.class);
    }

    @Override
    public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
        Rider currentRider = getCurrentRider();
        return rideService.getAllRidesOfRider(currentRider, pageRequest).map(
                ride -> modelMapper.map(ride, RideDto.class)
        );
    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider = Rider
                .builder()
                .user(user)
                .rating(0.0)
                .build();
        return riderRepository.save(rider);
    }

    @Override
    public Rider getCurrentRider() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return riderRepository.findByUser(user).orElseThrow(
                () -> new ResourceNotFoundException("Rider not associated with user with Id " + user.getId())
        );
    }
}
