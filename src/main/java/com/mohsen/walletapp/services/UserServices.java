package com.mohsen.walletapp.services;

import com.mohsen.walletapp.dtos.requestDtos.ActivateUserDto;
import com.mohsen.walletapp.dtos.requestDtos.ChangePasswordDto;
import com.mohsen.walletapp.dtos.requestDtos.UserLoginDto;
import com.mohsen.walletapp.dtos.requestDtos.UserSignupDto;
import com.mohsen.walletapp.dtos.responseDtos.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface UserServices {
    UserResponseDto signup(UserSignupDto userDto);

    String sendToken(String userEmail, String mailSubject);

    UserResponseDto activateUser(ActivateUserDto activateUserDto);

    UserResponseDto login(UserLoginDto userLoginDto);

//    String forgotPassword(String email);

    String resetPassword(ChangePasswordDto changePasswordDto);

    String logout(String token);

    UserResponseDto updatePassword(ChangePasswordDto changePasswordDto);
}
