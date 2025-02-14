package com.example.PaymentService.model;


import lombok.Data;

@Data
public class PaymentResponse {
    private Long userId;
    private double amount;
    private String message;

    public PaymentResponse(Long userId, double amount, String message) {
        this.userId = userId;
        this.amount = amount;
        this.message = message;
    }

    public PaymentResponse() {

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
