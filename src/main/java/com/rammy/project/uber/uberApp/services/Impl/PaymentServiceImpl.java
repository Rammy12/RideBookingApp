package com.rammy.project.uber.uberApp.services.Impl;


import com.rammy.project.uber.uberApp.entities.Payment;
import com.rammy.project.uber.uberApp.entities.Ride;
import com.rammy.project.uber.uberApp.entities.enums.PaymentStatus;
import com.rammy.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.rammy.project.uber.uberApp.repositories.PaymentRepository;
import com.rammy.project.uber.uberApp.services.PaymentService;
import com.rammy.project.uber.uberApp.strategies.PaymentStrategyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentStrategyManager paymentStrategyManager;
    @Override
    public void processPayment(Ride ride) {
        Payment payment = paymentRepository.findByRide(ride).orElseThrow(
                ()->new ResourceNotFoundException("Payment not found with ride :"+ride.getId())
        );
        paymentStrategyManager.paymentStrategy(payment.getPaymentMethod()).processPayment(payment);
    }

    @Override
    public Payment createNewpayment(Ride ride) {
        Payment payment = Payment.builder()
                .ride(ride)
                .paymentMethod(ride.getPaymentMethod())
                .amount(ride.getFare())
                .paymentStatus(PaymentStatus.PENDING)
                .build();
        return paymentRepository.save(payment);
    }

    @Override
    public void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus) {
        payment.setPaymentStatus(paymentStatus);
        paymentRepository.save(payment);
    }
}
