package com.rammy.project.uber.uberApp.dto;


import lombok.Data;

import java.util.List;

@Data
public class WalletDTO {
    private Long id;
    private UserDto user;
    private Double balance;
    private List<WalletTransactionDTO> transactions;
}
