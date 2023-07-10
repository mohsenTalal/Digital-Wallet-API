package com.mohsen.walletapp.services.impl;

import com.mohsen.walletapp.dtos.requestDtos.MailDto;
import com.mohsen.walletapp.dtos.responseDtos.ApiResponse;
import com.mohsen.walletapp.exceptions.MailSendingException;
import com.mohsen.walletapp.services.EmailServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//@RequiredArgsConstructor
@Transactional
public class EmailServicesImpl implements EmailServices {

    private final JavaMailSender mailSender;
    private final Logger logger = LoggerFactory.getLogger(EmailServicesImpl.class);

    public EmailServicesImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public ApiResponse<MailDto> sendEmail(MailDto mailDto) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("no-relpy-ewallet@gmail.com");
        simpleMailMessage.setTo(mailDto.getTo());
        simpleMailMessage.setSubject(mailDto.getSubject());
        simpleMailMessage.setText(mailDto.getMessage());

        try {
            mailSender.send(simpleMailMessage);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new MailSendingException(ex.getMessage());
        }

        logger.info("Email sent successfully to {}",mailDto.getTo());

        return new ApiResponse<>("Email sent successfully", true, mailDto);
    }

    public static void main(String[] args) {

    }
}
