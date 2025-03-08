package com.rammy.project.uber.uberApp.services;

import com.rammy.project.uber.uberApp.entities.Ride;
import com.rammy.project.uber.uberApp.entities.User;
import com.rammy.project.uber.uberApp.entities.Wallet;
import com.rammy.project.uber.uberApp.entities.enums.TransactionMethod;

public interface WalletService {

    Wallet addMoneyToWallet(User user, Double amount,
                            String transactionId, Ride ride,
                            TransactionMethod transactionMethod);
    Wallet deductMoneyFromWallet(User user, Double amount,
                                 String transactionId, Ride ride,
                                 TransactionMethod transactionMethod);

    Wallet findWalletById(Long walletId);

    Wallet createNewWallet(User user);

    void withdrawAllMyMoneyFromWallet();

    Wallet findByUser(User user);
}
