package com.example.PaymentService.dto;


import lombok.Data;

@Data
public class PaymentResponseDTO {
    private Long userId;
    private double amount;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PaymentResponseDTO(Long userId, double amount, String message) {
        this.userId = userId;
        this.amount = amount;
        this.message = message;
    }
    public PaymentResponseDTO() {

    }

    private String message;
}

