package com.rammy.project.uber.uberApp.repositories;

import com.rammy.project.uber.uberApp.entities.RideRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RideRequestRepository extends JpaRepository<RideRequest,Long> {
}
