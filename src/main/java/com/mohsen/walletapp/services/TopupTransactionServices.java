package com.mohsen.walletapp.services;

import com.mohsen.walletapp.dtos.clickpay.InitiateTransactionDto;
import org.springframework.stereotype.Service;

@Service
public interface TopupTransactionServices {
    InitiateTransactionDto logTransactionTopup(InitiateTransactionDto transactionDto);
}
