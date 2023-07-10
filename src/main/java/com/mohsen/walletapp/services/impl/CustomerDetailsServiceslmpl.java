package com.mohsen.walletapp.services.impl;

import com.mohsen.walletapp.repositories.CustomerDetailsRepository;
import com.mohsen.walletapp.services.CustomerDetailsServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerDetailsServiceslmpl implements CustomerDetailsServices {
    private CustomerDetailsRepository customerDetailsRepository;

    public CustomerDetailsServiceslmpl(CustomerDetailsRepository customerDetailsRepository){

        this.customerDetailsRepository = customerDetailsRepository;
    }
   /* @Override
    public CustomerDetailsRequestDto save(CustomerDetailsRequestDto customerDetailsRequestDto) {
        CustomerDetails customerDetails = appUtil.getMapper().
        customerDetails = CustomerDetailsRepository.save(customerDetails);

        return mapper.toDo(customerDetails);
    }*/
}
