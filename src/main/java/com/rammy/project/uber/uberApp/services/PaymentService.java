package com.rammy.project.uber.uberApp.services;

import com.rammy.project.uber.uberApp.entities.Payment;
import com.rammy.project.uber.uberApp.entities.Ride;
import com.rammy.project.uber.uberApp.entities.enums.PaymentStatus;

public interface PaymentService {

    void processPayment(Ride ride);

    Payment createNewpayment(Ride ride);

    void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus);
}
