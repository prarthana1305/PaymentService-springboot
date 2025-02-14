package com.example.PaymentService.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentTransactionDTO {
    private Long id;
    private Long userId;

    public PaymentTransactionDTO(Long id, Long userId, double amount, LocalDateTime timestamp) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public PaymentTransactionDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    private double amount;
    private LocalDateTime timestamp;
}
