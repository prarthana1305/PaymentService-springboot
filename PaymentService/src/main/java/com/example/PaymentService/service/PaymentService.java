package com.example.PaymentService.service;


import com.example.PaymentService.config.RabbitMQConfig;
import com.example.PaymentService.dto.CardStatusDTO;
import com.example.PaymentService.dto.PaymentRequestDTO;
import com.example.PaymentService.dto.PaymentResponseDTO;
import com.example.PaymentService.model.PaymentTransaction;
import com.example.PaymentService.repository.PaymentTransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service

public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);
    private final RabbitTemplate rabbitTemplate;

    public PaymentService(RabbitTemplate rabbitTemplate, PaymentTransactionRepository paymentTransactionRepository, RestTemplate restTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.paymentTransactionRepository = paymentTransactionRepository;
        this.restTemplate = restTemplate;
    }

    private final PaymentTransactionRepository paymentTransactionRepository;
    private final RestTemplate restTemplate;

    public PaymentResponseDTO processPayment(Long userId, PaymentRequestDTO requestDTO) {
        log.info("Processing payment for user: {}", userId);

        // Fetch user's card status from the User Service running on port 8081.
        String url = "http://localhost:8081/user/card/status/" + userId;
        ResponseEntity<CardStatusDTO> responseEntity = restTemplate.getForEntity(url, CardStatusDTO.class);
        CardStatusDTO cardStatus = responseEntity.getBody();
        log.info("Fetched card status for user {}: {}", userId, cardStatus);

        // Determine the payment method based on the cardType received from User Service.
        boolean isMetroCard = cardStatus != null && "metro".equalsIgnoreCase(cardStatus.getCardType());
        // (Even though the request body doesn't include the metroCard field, we decide it here.)
        log.info("User {} uses {} payment method", userId, isMetroCard ? "Metro Card" : "QR Ticket");

        // Calculate final amount based on payment method and conditions.
        double baseFare = 10.0;
        double discount = isMetroCard ? 0.9 : 1.0;  // 10% discount if metro card is used.
        double peakMultiplier = requestDTO.isPeakHour() ? 1.2 : 1.0; // 20% increase during peak hours.
        double finalAmount = baseFare * discount * peakMultiplier;

        // Optionally add extra amount based on travel duration (e.g., 0.5 per minute).
        finalAmount += requestDTO.getDurationInMinutes() * 0.5;
        log.info("Calculated payment amount: {} for user: {}", finalAmount, userId);

        // Create a payment transaction record and save it.
        PaymentTransaction transaction = new PaymentTransaction();
        transaction.setUserId(userId);
        transaction.setAmount(finalAmount);
        transaction.setTimestamp(LocalDateTime.now());
        paymentTransactionRepository.save(transaction);
        log.info("Saved payment transaction for user: {}", userId);

        // Create PaymentResponseDTO.
        PaymentResponseDTO responseDTO = new PaymentResponseDTO();
        responseDTO.setUserId(userId);
        responseDTO.setAmount(finalAmount);
        responseDTO.setMessage("Payment processed successfully");

        // Publish a payment success event to RabbitMQ.
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.PAYMENT_SUCCESS_ROUTING_KEY,
                responseDTO);
        log.info("Published payment success event for user: {}", userId);

        return responseDTO;
    }

    public List<PaymentTransaction> getPaymentHistory(Long userId) {
        List<PaymentTransaction> userTransactions = paymentTransactionRepository.findByUserId(userId);
        int size = userTransactions.size();
        List<PaymentTransaction> lastFive = new ArrayList<>();
        if (size > 5) {
            lastFive = userTransactions.subList(size - 5, size);
        } else {
            lastFive = userTransactions;
        }
        return lastFive;
    }
}
