package com.rammy.project.uber.uberApp.dto;

import com.rammy.project.uber.uberApp.entities.enums.TransacionType;
import com.rammy.project.uber.uberApp.entities.enums.TransactionMethod;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class WalletTransactionDTO {
    private Long id;
    private Double amount;
    private TransacionType transactionType;
    private TransactionMethod transactionMethod;
    private RideDto ride;
    private String transactionId;
    private WalletDTO wallet;
    private LocalDateTime timeStamp;
}
