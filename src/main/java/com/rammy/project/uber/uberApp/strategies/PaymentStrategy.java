package com.rammy.project.uber.uberApp.strategies;

import com.rammy.project.uber.uberApp.entities.Payment;

public interface PaymentStrategy {

    Double PLATFORM_COMMISSION = 0.292;
    void processPayment(Payment payment);
}
