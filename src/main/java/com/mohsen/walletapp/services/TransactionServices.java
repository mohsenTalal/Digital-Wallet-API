package com.mohsen.walletapp.services;

import com.mohsen.walletapp.dtos.clickpay.InitiateTransactionDto;
import com.mohsen.walletapp.dtos.requestDtos.TransactionRequestDto;
import com.mohsen.walletapp.dtos.responseDtos.TransactionResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface TransactionServices {

    TransactionResponseDto logTransaction(TransactionResponseDto transactionResponseDto);

    TransactionRequestDto logTransactionV2(TransactionRequestDto transactionRequestDto);

}
