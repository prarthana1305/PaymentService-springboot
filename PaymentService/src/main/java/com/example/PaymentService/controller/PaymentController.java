package com.example.PaymentService.controller;

import com.example.PaymentService.dto.PaymentRequestDTO;
import com.example.PaymentService.dto.PaymentResponseDTO;
import com.example.PaymentService.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")

public class PaymentController {
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    private final PaymentService paymentService;

    // Endpoint to process a payment
    @PostMapping("/pay/{userId}")
    public ResponseEntity<PaymentResponseDTO> processPayment(@PathVariable Long userId,
                                                             @RequestBody PaymentRequestDTO requestDTO) {
        PaymentResponseDTO responseDTO = paymentService.processPayment(userId, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    // Endpoint to get payment history for a user
    @GetMapping("/history/{userId}")
    public ResponseEntity<?> getPaymentHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(paymentService.getPaymentHistory(userId));
    }
}
