package com.mohsen.walletapp.services.impl;

import com.mohsen.walletapp.dtos.requestDtos.*;
import com.mohsen.walletapp.dtos.requestDtos.*;
import com.mohsen.walletapp.dtos.responseDtos.UserResponseDto;
import com.mohsen.walletapp.enums.Roles;
import com.mohsen.walletapp.enums.Status;
import com.mohsen.walletapp.exceptions.AuthenticationException;
import com.mohsen.walletapp.exceptions.NotFoundException;
import com.mohsen.walletapp.exceptions.ValidationException;
import com.mohsen.walletapp.models.User;
import com.mohsen.walletapp.models.Wallet;
import com.mohsen.walletapp.repositories.UserRepository;
import com.mohsen.walletapp.repositories.WalletRepository;
import com.mohsen.walletapp.security.CustomUserDetailsServices;
import com.mohsen.walletapp.security.JwtUtil;
import com.mohsen.walletapp.services.UserServices;
import com.mohsen.walletapp.utils.AppUtil;
import com.mohsen.walletapp.utils.LocalMemStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
@Transactional
public class UserServicesImpl implements UserServices {

    private final AuthenticationManager auth;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final CustomUserDetailsServices userDetailsServices;
    private final EmailServicesImpl mailSender;
    private final WalletRepository walletRepository;
    private final AppUtil app;
    private final LocalMemStorage memStorage;
    private final PasswordEncoder passwordEncoder;
    private final Logger logger = LoggerFactory.getLogger(UserServicesImpl.class);

    public UserServicesImpl(AuthenticationManager auth, JwtUtil jwtUtil, UserRepository userRepository, CustomUserDetailsServices userDetailsServices, EmailServicesImpl mailSender, WalletRepository walletRepository, AppUtil app, LocalMemStorage memStorage, PasswordEncoder passwordEncoder) {
        this.auth = auth;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.userDetailsServices = userDetailsServices;
        this.mailSender = mailSender;
        this.walletRepository = walletRepository;
        this.app = app;
        this.memStorage = memStorage;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserResponseDto signup(UserSignupDto userDto) {

        if (!app.validEmail(userDto.getEmail())) {
            throw new ValidationException("Invalid email address {"+userDto.getEmail()+"}");
        }

        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new ValidationException("User with email: " + userDto.getEmail() + " already exists");
        }

        User newUser = app.getMapper().convertValue(userDto, User.class);
        String userId = app.generateSerialNumber("usr");
        newUser.setUuid(userId);
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setRoles(Roles.USER.name());
        newUser.setStatus(Status.INACTIVE.name());

        newUser = userRepository.save(newUser);

        sendToken(newUser.getEmail(), "Activate your account");

        return app.getMapper().convertValue(newUser, UserResponseDto.class);
    }

    @Override
    public String sendToken(String userEmail, String subject) {
        if (!userRepository.existsByEmail(userEmail))
            throw new ValidationException("User with email: " + userEmail + " does not exists");

        String token = app.generateSerialNumber("verify");
        memStorage.save(userEmail, token, 900); //expires in 15mins

        MailDto mailDto = MailDto.builder()
                .to(userEmail)
                .subject(subject.toUpperCase())
                .message(String.format("Use this generated token to %s: %s (Expires in 15mins)", subject.toLowerCase(), token))
                .build();

        mailSender.sendEmail(mailDto);

        return "Verification Token sent";
    }

    @Override
    public UserResponseDto activateUser(ActivateUserDto activateUserDto) {

        validateToken(activateUserDto.getEmail(), activateUserDto.getVerificationToken());

        User userToActivate = userRepository.findByEmail(activateUserDto.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found"));

        userToActivate.setStatus(Status.ACTIVE.name());
        UserResponseDto userResponseDto = app.getMapper().convertValue(userRepository.save(userToActivate), UserResponseDto.class);


        Wallet newWallet = Wallet.builder()
                .walletUUID(app.generateSerialNumber("0"))
                .balance(BigDecimal.ZERO)
                .email(activateUserDto.getEmail())
                .build();

        Optional<User> user =  userRepository.findByEmail(activateUserDto.getEmail());

        final Long id = Long.valueOf(String.valueOf(userToActivate.getId()));



        walletRepository.save(newWallet);

        MailDto mailDto = MailDto.builder()
                .to(userToActivate.getEmail())
                .subject("This's Sam Valley E-Wallet system, YOUR ACCOUNT IS ACTIVATED")
                .message(String.format("Hi %s, \n You have successfully activated your account in Sam Valley e-wallet. Kindly login to start making use of the app", userResponseDto.getFirstName()))
                .build();

        mailSender.sendEmail(mailDto);

        return userResponseDto;
    }

    @Override
    public UserResponseDto login(UserLoginDto userLoginDto) {

        if (!app.validEmail(userLoginDto.getEmail()))
            throw new ValidationException("Invalid email");
        try {
            Authentication authentication = auth.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword())
            );
            UserResponseDto userResponseDto = null;

            if (authentication.isAuthenticated()) {
                User user = userRepository.findByEmail(userLoginDto.getEmail())
                        .orElseThrow(() -> new AuthenticationException("Invalid login credentials"));

                if (user.getStatus().equals(Status.INACTIVE.name()))
                    throw new ValidationException("User not active");

                logger.info("Generating access token for {}", user.getEmail());

                String accessToken = jwtUtil.generateToken(userDetailsServices.loadUserByUsername(user.getEmail()));

                user.setLastLoginDate(new Date());
                userResponseDto = app.getMapper().convertValue(userRepository.save(user), UserResponseDto.class);

                userResponseDto.setToken(accessToken);
            } else {
                throw new AuthenticationException("Invalid username or password");
            }
            return userResponseDto;

        } catch (Exception e) {
            throw new AuthenticationException(e.getMessage());
        }
    }

    @Override
    public String resetPassword(ChangePasswordDto changePasswordDto) {
        validateToken(changePasswordDto.getEmail(), changePasswordDto.getVerificationToken());

        User userToResetPassword = userRepository.findByEmail(changePasswordDto.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found"));

        userToResetPassword.setPassword(passwordEncoder.encode(changePasswordDto.getPassword()));
        userRepository.save(userToResetPassword);

        MailDto mailDto = MailDto.builder()
                .to(userToResetPassword.getEmail())
                .subject("PASSWORD RESET SUCCESSFUL")
                .message(String.format("Hi %s, \n You have successfully reset your password. Kindly login with your new password. " +
                        "\n If you did not authorize this, kindly create a ticket from our complaint section on the app", userToResetPassword.getFirstName()))
                .build();

        mailSender.sendEmail(mailDto);

        return "password reset successful";
    }

    @Override
    public String logout(String headerToken) {
        if (headerToken.startsWith("Bearer")) {
            headerToken = headerToken.replace("Bearer", "").replace("\\s", "");
        }
        blacklistToken(headerToken);
        return "Logout successful";
    }

    @Override
    public UserResponseDto updatePassword(ChangePasswordDto changePasswordDto) {

        if (jwtUtil.isTokenExpired(changePasswordDto.getVerificationToken()))
            throw new ValidationException("Request token has expired");

        blacklistToken(changePasswordDto.getVerificationToken());

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String newToken = jwtUtil.generateToken(userDetails);

        UserResponseDto userResponseDto = app.getMapper().convertValue(userRepository.findByEmail(changePasswordDto.getEmail())
                .orElseThrow(() -> new AuthenticationException("User does not exist")), UserResponseDto.class);
        userResponseDto.setToken(newToken);

        return userResponseDto;
    }

    public void validateToken(String memCachedKey, String token) {

        if (!app.validEmail(memCachedKey))
            throw new ValidationException("Invalid email");

        String cachedToken = memStorage.getValueByKey(memCachedKey);
        if (cachedToken == null)
            throw new ValidationException("Token expired");
        if (!cachedToken.equals(token))
            throw new ValidationException("Invalid token");

        if (!userRepository.existsByEmail(memCachedKey))
            throw new NotFoundException("User not found");
    }

    public void blacklistToken(String token) {
        Date expiryDate = jwtUtil.extractExpiration(token);
        int expiryTimeInSeconds = (int) ((expiryDate.getTime() - new Date().getTime())/1000);
        memStorage.setBlacklist(token, expiryTimeInSeconds);
    }
}