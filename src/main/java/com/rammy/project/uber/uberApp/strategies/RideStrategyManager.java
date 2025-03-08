package com.rammy.project.uber.uberApp.strategies;

import com.rammy.project.uber.uberApp.strategies.Impl.DriverMatchingHighestRatedDiverStrategy;
import com.rammy.project.uber.uberApp.strategies.Impl.DriverMatchingNearestDiverStrategy;
import com.rammy.project.uber.uberApp.strategies.Impl.RideFareDefaultCalculationStrategy;
import com.rammy.project.uber.uberApp.strategies.Impl.RideFareSurgePricingCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class RideStrategyManager {
    private final DriverMatchingNearestDiverStrategy driverMatchingNearestDiverStrategy;
    private final DriverMatchingHighestRatedDiverStrategy driverMatchingHighestRatedDiverStrategy;
    private final RideFareDefaultCalculationStrategy rideFareDefaultCalculationStrategy;
    private final RideFareSurgePricingCalculationStrategy rideFareSurgePricingCalculationStrategy;
    public DriverMatchingStrategy driverMatchingStrategy(double riderRating){
        if (riderRating >= 4.8){
            return driverMatchingHighestRatedDiverStrategy;
        }
        else{
            return driverMatchingNearestDiverStrategy;
        }

    }
    public RideFareCalculation rideFareCalculation(){
        LocalTime surgeStartTime = LocalTime.of(18,0);
        LocalTime surgeEndTime = LocalTime.of(21,0);
        LocalTime currentTime = LocalTime.now();
        boolean isSurgeTime = currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime);

        if (isSurgeTime){
            return rideFareSurgePricingCalculationStrategy;
        }
        else {
            return rideFareDefaultCalculationStrategy;
        }
    }

}
