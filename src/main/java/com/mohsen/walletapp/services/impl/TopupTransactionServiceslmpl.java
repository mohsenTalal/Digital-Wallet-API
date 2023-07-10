package com.mohsen.walletapp.services.impl;

import com.mohsen.walletapp.dtos.clickpay.InitiateTransactionDto;
import com.mohsen.walletapp.models.TopupCustomerdetails;
import com.mohsen.walletapp.models.TopupTxnHistory;
import com.mohsen.walletapp.repositories.TopupTransactionRepository;
import com.mohsen.walletapp.services.TopupTransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TopupTransactionServiceslmpl implements TopupTransactionServices {

    @Autowired
    private TopupTransactionRepository topupTransactionRepository;
    @Override
    public InitiateTransactionDto logTransactionTopup(InitiateTransactionDto transactionDto) {
      /*  if (!topupTransactionRepository
                .existsByTranRefOrId(transactionDto.getTranRef(), transactionDto.getId())) {
            //TopupTransactions topupTransactions = appUtil.getMapper().convertValue(transactionDto, TopupTransactions.class);

            TopupTransactions topupTransactions = mapManualTopup(transactionDto);
            topupTransactionRepository.save(topupTransactions);
        }*/
        TopupTxnHistory topupTxnHistory = mapManualTopup(transactionDto);

        topupTransactionRepository.save(topupTxnHistory);
        return transactionDto;
    }

    private TopupTxnHistory mapManualTopup(InitiateTransactionDto transactionDto) {

        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("logTransactionTopup:" + transactionDto );
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%");

        TopupTxnHistory topupTxnHistory = new TopupTxnHistory();

      topupTxnHistory.setId(null);
        topupTxnHistory.setTranRef(transactionDto.getTran_ref());
       //topupTransactions.setMerchantId(transactionDto.getMerchantId());
        topupTxnHistory.setProfileId(transactionDto.getProfile_id());
        topupTxnHistory.setCartId(transactionDto.getCart_id());
        topupTxnHistory.setCartDescription(transactionDto.getCart_description());
        topupTxnHistory.setCartCurrency(transactionDto.getCart_currency());
        topupTxnHistory.setCartAmount(transactionDto.getCart_amount());
        topupTxnHistory.setTranCurrency(transactionDto.getCart_currency());
        //topupTransactions.setTranTotal(transactionDto.getTranTotal());
        topupTxnHistory.setTranType(transactionDto.getTran_type());
        //topupTransactions.setTranClass(transactionRequestDto.getTranTotal());
        //topupTransactions.setIpnTrace(transactionDto.getIpnTrace());

        topupTxnHistory.setTopupCustomerDetails(new TopupCustomerdetails(null,
                transactionDto.getCustomerDetails().getName(),
                transactionDto.getCustomerDetails().getEmail(),
                transactionDto.getCustomerDetails().getState(),
                transactionDto.getCustomerDetails().getCity(),
                transactionDto.getCustomerDetails().getState(),
                transactionDto.getCustomerDetails().getCountry(),
                transactionDto.getCustomerDetails().getIp()
        ));

        return topupTxnHistory;
    }
}
