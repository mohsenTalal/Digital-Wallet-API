package com.mohsen.walletapp.services;

import com.mohsen.walletapp.dtos.responseDtos.WalletResponseDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface WalletServices {

    WalletResponseDto updateWallet(String email, BigDecimal amount);

    BigDecimal getWalletBalance(String email);
}
