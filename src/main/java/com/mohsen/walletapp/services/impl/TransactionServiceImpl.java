package com.mohsen.walletapp.services.impl;

import com.mohsen.walletapp.dtos.requestDtos.TransactionRequestDto;
import com.mohsen.walletapp.dtos.responseDtos.TransactionResponseDto;
import com.mohsen.walletapp.models.*;
import com.mohsen.walletapp.repositories.TransactionRepository;
import com.mohsen.walletapp.services.TransactionServices;
import com.mohsen.walletapp.utils.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionServices {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AppUtil appUtil;

    @Override
    public TransactionResponseDto logTransaction(TransactionResponseDto transactionResponseDto) {

        if (!transactionRepository
                .existsByTranRef(transactionResponseDto.getTranRef())) {

            Transactions transaction = appUtil.getMapper().convertValue(transactionResponseDto, Transactions.class);

            //transaction.setCustomerDetails(transactionResponseDto.getCustomerDetailsResponseDto().getEmail());
            transactionRepository.save(transaction);
        }

        return transactionResponseDto;
    }

    @Override
    public TransactionRequestDto logTransactionV2(TransactionRequestDto transactionRequestDto) {

        if (!transactionRepository
                .existsByTranRef(transactionRequestDto.getTranRef())) {
            //Transactions transaction = appUtil.getMapper().convertValue(transactionRequestDto, Transactions.class);
            Transactions transaction = mapManual(transactionRequestDto);
            //transaction.setCustomerDetails(transactionResponseDto.getCustomerDetailsResponseDto().getEmail());
            transactionRepository.save(transaction);
        }
        return transactionRequestDto;
    }
    public Transactions mapManual(TransactionRequestDto transactionRequestDto){

        Transactions transactions = new Transactions();

        transactions.setTranRef(transactionRequestDto.getTranRef());
        transactions.setMerchantId(transactionRequestDto.getMerchantId());
        transactions.setProfileId(transactionRequestDto.getProfileId());
        transactions.setCartId(transactionRequestDto.getCartId());
        transactions.setCartDescription(transactionRequestDto.getCartDescription());
        transactions.setCartCurrency(transactionRequestDto.getCartCurrency());
        transactions.setCartAmount(String.valueOf(transactionRequestDto.getCartAmount()));
        transactions.setTranCurrency(transactionRequestDto.getTranCurrency());
        transactions.setTranTotal(transactionRequestDto.getTranTotal());
        transactions.setTranType(transactionRequestDto.getTranType());
        transactions.setTranClass(transactionRequestDto.getTranTotal());
        transactions.setIpnTrace(transactionRequestDto.getIpnTrace());

        transactions.setCustomerDetails(new CustomerDetails(null,
                transactionRequestDto.getCustomerDetailsDto().getName(),
                transactionRequestDto.getCustomerDetailsDto().getEmail(),
                transactionRequestDto.getCustomerDetailsDto().getState(),
                transactionRequestDto.getCustomerDetailsDto().getCity(),
                transactionRequestDto.getCustomerDetailsDto().getState(),
                transactionRequestDto.getCustomerDetailsDto().getCountry(),
                transactionRequestDto.getCustomerDetailsDto().getIp()
                ));

        transactions.setPaymentResult(new PaymentResult(null,
                transactionRequestDto.getPaymentResultRequestDto().getResponseStatus(),
                transactionRequestDto.getPaymentResultRequestDto().getResponseCode(),
                transactionRequestDto.getPaymentResultRequestDto().getResponseMessage(),
                transactionRequestDto.getPaymentResultRequestDto().getCvvResult(),
                transactionRequestDto.getPaymentResultRequestDto().getAvsResult(),
                transactionRequestDto.getPaymentResultRequestDto().getTransactionTime()
                ));

        transactions.setPaymentInfo(new PaymentInfo(null,
                transactionRequestDto.getPaymentInfoRequestDto().getPaymentMethod(),
                transactionRequestDto.getPaymentInfoRequestDto().getCardType(),
                transactionRequestDto.getPaymentInfoRequestDto().getCardScheme(),
                transactionRequestDto.getPaymentInfoRequestDto().getPaymentDescription(),
                transactionRequestDto.getPaymentInfoRequestDto().getExpiryMonth(),
                transactionRequestDto.getPaymentInfoRequestDto().getExpiryYear()
                ));

        return transactions;
    }
}
