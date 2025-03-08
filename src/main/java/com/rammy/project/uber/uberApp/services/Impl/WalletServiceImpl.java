package com.rammy.project.uber.uberApp.services.Impl;

import com.rammy.project.uber.uberApp.dto.RideDto;
import com.rammy.project.uber.uberApp.dto.WalletTransactionDTO;
import com.rammy.project.uber.uberApp.entities.Ride;
import com.rammy.project.uber.uberApp.entities.User;
import com.rammy.project.uber.uberApp.entities.Wallet;
import com.rammy.project.uber.uberApp.entities.WalletTransaction;
import com.rammy.project.uber.uberApp.entities.enums.TransacionType;
import com.rammy.project.uber.uberApp.entities.enums.TransactionMethod;
import com.rammy.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.rammy.project.uber.uberApp.repositories.WalletRepository;
import com.rammy.project.uber.uberApp.services.WalletService;
import com.rammy.project.uber.uberApp.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    private final ModelMapper modelMapper;
    private final WalletTransactionService walletTransactionService;
    @Override
    @Transactional
    public Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet= findByUser(user);
        wallet.setBalance(wallet.getBalance()+amount);
        WalletTransaction walletTransaction = WalletTransaction
                .builder()
                .transactionId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transactionType(TransacionType.CREDIT)
                .transactionMethod(transactionMethod)
                .build();
//        walletTransactionService.createNewWalletTransaction(walletTransaction);
        wallet.getTransactions().add(walletTransaction);
        return walletRepository.save(wallet);
    }

    @Override
    @Transactional
    public Wallet deductMoneyFromWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet= findByUser(user);
        wallet.setBalance(wallet.getBalance()-amount);
        WalletTransaction walletTransaction = WalletTransaction
                .builder()
                .transactionId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transactionType(TransacionType.DEBIT)
                .transactionMethod(transactionMethod)
                .build();
        walletTransactionService.createNewWalletTransaction(walletTransaction);
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet findWalletById(Long walletId) {
        return walletRepository.findById(walletId).orElseThrow(
                ()->new ResourceNotFoundException("Wallet not found with id "+walletId)
        );
    }
    @Override
    public Wallet createNewWallet(User user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        return walletRepository.save(wallet);
    }
    @Override
    public void withdrawAllMyMoneyFromWallet() {

    }

    @Override
    public Wallet findByUser(User user) {
        return walletRepository.findByUser(user)
                .orElseThrow(()->new ResourceNotFoundException("Wallet not found for user with id "+ user.getId()));
    }
}
