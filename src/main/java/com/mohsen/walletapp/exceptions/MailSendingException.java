package com.mohsen.walletapp.exceptions;

public class MailSendingException extends RuntimeException {

    public MailSendingException(String errorMessage) {
        super(errorMessage);
    }
}
