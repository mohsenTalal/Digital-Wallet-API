package com.mohsen.walletapp.services.impl;

import com.mohsen.walletapp.dtos.clickpay.*;
import com.mohsen.walletapp.dtos.requestDtos.TransactionRequestDto;
import com.mohsen.walletapp.dtos.requestDtos.TrxQueryRequestDto;
import com.mohsen.walletapp.dtos.requestDtos.WalletTransferDto;
import com.mohsen.walletapp.dtos.responseDtos.*;
import com.mohsen.walletapp.enums.TransactionType;
import com.mohsen.walletapp.exceptions.InvalidTransactionException;
import com.mohsen.walletapp.exceptions.NotFoundException;
import com.mohsen.walletapp.exceptions.ValidationException;
import com.mohsen.walletapp.repositories.TransactionRepository;
import com.mohsen.walletapp.repositories.UserRepository;
import com.mohsen.walletapp.repositories.WalletRepository;
import com.mohsen.walletapp.security.JwtFilter;
import com.mohsen.walletapp.security.JwtUtil;
import com.mohsen.walletapp.services.PaymentServices;
import com.mohsen.walletapp.services.TopupTransactionServices;
import com.mohsen.walletapp.services.TransactionServices;
import com.mohsen.walletapp.services.WalletServices;
import com.mohsen.walletapp.utils.AppUtil;
import com.mohsen.walletapp.utils.LocalMemStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class PaymentServicesImpl implements PaymentServices {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionServices transactionServices;

    @Autowired
    private TopupTransactionServices topupTransactionServices;
    @Autowired
    private LocalMemStorage localMemStorage;

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;
    @Value("${clickpay.secretkey}")
    private String apiKey;
    @Autowired
    private WalletServices walletService;
    @Autowired
    private AppUtil appUtil;

    public PaymentServicesImpl() {
    }


    @Override
    public ApiResponse<List<BankDto>> fetchBanks(String currency, String type) {
        //todo: not yet implement !
        String url = "https://NotYet/sa?currency="+currency+((type == null)? "": "&type="+type);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+apiKey);

        HttpEntity entity = new HttpEntity<>(headers);

        appUtil.log("Fetching available banks");

        ResponseEntity<BankListApiResponseDto> apiResponse = restTemplate.exchange(url, HttpMethod.GET, entity, BankListApiResponseDto.class);

        List<BankDto> banks = Objects.requireNonNull(apiResponse.getBody()).getData().stream()
                .map((bank -> appUtil.getMapper().convertValue(bank, BankDto.class)))
                .filter(BankDto::isActive)
                .collect(Collectors.toList());

        return new ApiResponse<>(apiResponse.getBody().getMessage(), apiResponse.getBody().isStatus(), banks);
    }

    @Override
    public PaymentLinkResponseDto getPaymentLink(InitiateTransactionDto transactionDto) {
        String url = "https://secure.clickpay.com.sa/payment/request";

        transactionDto.setCart_amount(Double.valueOf(transactionDto.getCart_amount()+"00"));
        //Set authorization header for querying clickpay's end points

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "S9JNLLMRMR-J62L2KZZ6D-LKNHTMMD6T" );
        headers.set("Content-Type","application/json");

        HttpEntity<InitiateTransactionDto> entity = new HttpEntity<>(transactionDto,headers);
        appUtil.log("Generating payment url for: " + transactionDto.getCustomerDetails());

        RestTemplate restTemplate = new RestTemplate();
        PaymentLinkResponseDto paymentLinkResponseDto = restTemplate.exchange(url, HttpMethod.POST,
                entity, PaymentLinkResponseDto.class).getBody();

        if (paymentLinkResponseDto != null){
            transactionDto.setTran_type(TransactionType.TRANSACTION_TYPE_DEPOSIT.getTransaction());
            topupTransactionServices.logTransactionTopup(transactionDto);
        }

        return paymentLinkResponseDto;
    }
    @Override
    public ApiResponse<TransactionResponseDto> verifyTransaction(String payment_reference) {
        String url = "https://secure.clickpay.com.sa/payment/request" + payment_reference;

        //Set authorization header for querying Clickpay's end points
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + apiKey);
        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity<ApiResponse> transactionResponseDto =
                restTemplate.exchange(url, HttpMethod.GET, entity, ApiResponse.class);

        TransactionResponseDto responseData = appUtil.getMapper().convertValue(Objects
                .requireNonNull(transactionResponseDto.getBody()).getData(), TransactionResponseDto.class);
        responseData.setCartAmount(responseData.getCartAmount().divide(new BigDecimal(100), 2, RoundingMode.DOWN));

        appUtil.print("Transaction verification data: "+transactionResponseDto.getBody());

        //if transaction is not logged, log it...
        if (!transactionRepository
                .existsByTranRef(responseData.getTranRef())) {

            if (responseData.getPaymentResultResponseDto().getResponseStatus().equalsIgnoreCase("success")) { // Update wallet only when transaction is fulfilled
                walletService.updateWallet(responseData.getCustomerDetailsResponseDto().getEmail(), responseData.getCartAmount());
            }
            //save transaction to DB for transaction history purposes
            responseData.setTranType(TransactionType.TRANSACTION_TYPE_DEPOSIT.getTransaction());
            //Just to test
            transactionServices.logTransaction(responseData);
        }

        return new ApiResponse<>(responseData.getPaymentResultResponseDto().getResponseMessage(),
                responseData.getPaymentResultResponseDto().getResponseStatus().equalsIgnoreCase("success"),responseData);
    }

    @Override
    public ApiResponse<TrxQueryResponseDto> getQueryTransaction(TrxQueryRequestDto trxQueryRequestDto) {

        String endPoint = "https://secure.clickpay.com.sa/payment/query";

        //Set authorization header for querying clickpay's end points
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "S9JNLLMRMR-J62L2KZZ6D-LKNHTMMD6T" );
        headers.set("Content-Type","application/json");

        HttpEntity<TrxQueryRequestDto> entity = new HttpEntity<>(trxQueryRequestDto,headers);
        //Retrieve query transaction details from clickpay's API
        ResponseEntity<ApiResponse> responseEntity =
                restTemplate.exchange(endPoint, HttpMethod.POST, entity, ApiResponse.class);

        //Map response data to trxQueryRequestDto
        TrxQueryResponseDto trxQueryResponseDto = appUtil.getMapper().convertValue(Objects
                .requireNonNull(responseEntity.getBody()).getData(), TrxQueryResponseDto.class);

        return new ApiResponse<>(responseEntity.getBody().getMessage(), responseEntity.getBody().isStatus(), trxQueryResponseDto);
    }

    @Override
    public ApiResponse<FundTransferDto> createTransferRecipient(AccountDto accountDto) {
        String endPoint = "https://secure.clickpay.com.sa/payment/request";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + apiKey);
        HttpEntity<AccountDto> entity = new HttpEntity<>(accountDto,headers);

        ResponseEntity<ApiResponse> apiResponse = restTemplate.exchange(endPoint, HttpMethod.POST, entity, ApiResponse.class);

        TransferRecipientDto transferRecipientDto = appUtil.getMapper()
                .convertValue(Objects.requireNonNull(apiResponse.getBody()).getData(), TransferRecipientDto.class);

        //Save a reference code to this transfer in memcache for use while initiating the transfer
        String transferUniqueReference = appUtil.generateSerialNumber("TRF_");
        localMemStorage.save(transferRecipientDto.getRecipient_code(), transferUniqueReference, 3600);

        FundTransferDto fundTransferDto = FundTransferDto.builder()
                .recipient(transferRecipientDto.getRecipient_code())
                .reference(transferUniqueReference)
                .build();

        return new ApiResponse<>(apiResponse.getBody().getMessage(), apiResponse.getBody().isStatus(), fundTransferDto);
    }

    @Override
    public ApiResponse<TransactionResponseDto> initiateTransfer(FundTransferDto fundTransferDto) {
        if (!localMemStorage.keyExist(fundTransferDto.getRecipient())) {
            throw new InvalidTransactionException("Transfer session has expired please try again");
        }

        BigDecimal balance = walletRepository.findByEmail(fundTransferDto.getEmail()).getBalance();

        //BigDecimal balance = walletRepository.findByEmail(fundTransferDto.getEmail()).map(Wallet::getBalance).orElse(BigDecimal.ZERO);
        //// remove this and create mapper in service layer.
        if (balance.compareTo(fundTransferDto.getAmount()) < 0)
            throw new InvalidTransactionException("Insufficient balance");

        localMemStorage.clear(fundTransferDto.getRecipient());

        String endPoint = "https://secure.clickpay.com.sa/payment/request";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + apiKey);
        HttpEntity<FundTransferDto> entity = new HttpEntity<>(fundTransferDto,headers);
        ResponseEntity<ApiResponse> apiResponse = null;
        try {

            apiResponse = restTemplate.exchange(endPoint, HttpMethod.POST, entity, ApiResponse.class);

        } catch (Exception e) {
            throw  new NotFoundException(e.getMessage()+". Sorry about that. This is just a test API, but your transfer would be processed if this was a production app");
        }
        TransactionResponseDto transactionResponseDto = appUtil.getMapper()
                .convertValue(Objects.requireNonNull(apiResponse.getBody()).getData(), TransactionResponseDto.class);
        transactionResponseDto.setTranType(TransactionType.TRANSACTION_TYPE_TRANSFER.getTransaction());
        transactionResponseDto.setCartAmount(transactionResponseDto.getCartAmount().divide(new BigDecimal(100), 2, RoundingMode.DOWN));

        return new ApiResponse<>(apiResponse.getBody().getMessage(),
                apiResponse.getBody().isStatus(), transactionServices.logTransaction(new TransactionResponseDto()));

    }

    @Override
    public boolean updatePaymentCallback(TransactionRequestDto transactionRequestDto) {
        boolean action = false;
        if (!transactionRepository
                .existsByTranRef(transactionRequestDto.getTranRef())) {
            if (transactionRequestDto.getPaymentResultRequestDto().getResponseStatus().equalsIgnoreCase("success")) {
                // Update wallet only when transaction is fulfilled
                walletService.updateWallet(transactionRequestDto.getCustomerDetailsDto().getEmail(), transactionRequestDto.getCartAmount());
            }
            //then save transaction to DB for transaction history purposes
            transactionRequestDto.setTranType(TransactionType.TRANSACTION_TYPE_DEPOSIT.getTransaction());
            transactionServices.logTransactionV2(transactionRequestDto);
            action = true;
        }
        return action;
    }

    @Override
    public WalletTransferResponseDto initiateWalletTransfer(WalletTransferDto walletTransferDto) {
        if (!userRepository.existsByEmail(walletTransferDto.getEmail())) {
            throw new ValidationException("User not exists");
        }

        BigDecimal balance = walletService.getWalletBalance(JwtUtil.getCurrentUserLogin().get());
        double res = walletTransferDto.getAmount().compareTo(balance);
        String str1 = "Both values are equal or the user's wallet is grater than ";
       if(res == 0 || res == -1){
          System.out.println( str1 );
       }
       else if(res == 1){
           System.out.println("Your balance is not enough");
       }

        return null;
    }
}














































