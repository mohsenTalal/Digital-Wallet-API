package com.mohsen.walletapp.services.impl;

import com.mohsen.walletapp.dtos.responseDtos.WalletResponseDto;
import com.mohsen.walletapp.models.Wallet;
import com.mohsen.walletapp.repositories.WalletRepository;
import com.mohsen.walletapp.services.WalletServices;
import com.mohsen.walletapp.utils.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class WalletServiceImpl implements WalletServices {
    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private AppUtil appUtil;
    @Override
    public WalletResponseDto updateWallet(String email, BigDecimal amount) {

        Wallet wallet = walletRepository.findByEmail(email);
/*                .orElse(Wallet.builder()
                        .walletUUID(appUtil.generateSerialNumber("0"))
                        .balance(BigDecimal.ZERO)
                        .email(email)
                        .build());*/

        wallet.setBalance(wallet.getBalance().add(amount));
        //wallet.setUpdatedAt();

        return appUtil.getMapper().convertValue(walletRepository.save(wallet), WalletResponseDto.class);
    }

    @Override
    public BigDecimal getWalletBalance(String email) {
        Wallet wallet = walletRepository.findByEmail(email);
        return wallet.getBalance();
    }
}
