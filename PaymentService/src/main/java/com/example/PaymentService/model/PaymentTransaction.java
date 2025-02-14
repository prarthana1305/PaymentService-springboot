package com.example.PaymentService.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment_transactions")
@Data
public class PaymentTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public PaymentTransaction(Long id, Long userId, double amount, LocalDateTime timestamp) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public PaymentTransaction() {

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

    private Long userId;
    private double amount;
    private LocalDateTime timestamp;
}
