package com.mohsen.walletapp.services;

import com.mohsen.walletapp.dtos.requestDtos.MailDto;
import com.mohsen.walletapp.dtos.responseDtos.ApiResponse;
import org.springframework.stereotype.Service;

@Service
public interface EmailServices {

    ApiResponse<MailDto> sendEmail(MailDto mailDto);
}
