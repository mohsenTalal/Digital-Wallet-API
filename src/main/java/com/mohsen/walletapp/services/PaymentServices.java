package com.mohsen.walletapp.services;


import com.mohsen.walletapp.dtos.clickpay.*;
import com.mohsen.walletapp.dtos.clickpay.AccountDto;
import com.mohsen.walletapp.dtos.clickpay.BankDto;
import com.mohsen.walletapp.dtos.clickpay.FundTransferDto;
import com.mohsen.walletapp.dtos.clickpay.InitiateTransactionDto;
import com.mohsen.walletapp.dtos.requestDtos.TransactionRequestDto;
import com.mohsen.walletapp.dtos.requestDtos.TrxQueryRequestDto;
import com.mohsen.walletapp.dtos.requestDtos.WalletTransferDto;
import com.mohsen.walletapp.dtos.responseDtos.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentServices {

    ApiResponse<List<BankDto>> fetchBanks(String currency, String type);

    PaymentLinkResponseDto getPaymentLink(InitiateTransactionDto transactionDto);

    ApiResponse<TransactionResponseDto> verifyTransaction(String paymentReference);

    ApiResponse<TrxQueryResponseDto> getQueryTransaction(TrxQueryRequestDto trxQueryRequestDto);

    ApiResponse<FundTransferDto> createTransferRecipient(AccountDto accountDto);

    ApiResponse<TransactionResponseDto> initiateTransfer(FundTransferDto fundTransferDto);

    boolean updatePaymentCallback(TransactionRequestDto transactionRequestDto);

    WalletTransferResponseDto initiateWalletTransfer(WalletTransferDto walletTransferDto);
}
