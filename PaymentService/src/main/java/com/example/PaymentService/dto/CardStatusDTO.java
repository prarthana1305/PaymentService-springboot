package com.example.PaymentService.dto;


import lombok.Data;

@Data
public class CardStatusDTO {
    private Long userId;
    private String cardType; // Expected values: "metro" or "qr"

    public Long getUserId() {
        return userId;
    }

    public CardStatusDTO(Long userId, String cardType) {
        this.userId = userId;
        this.cardType = cardType;
    }

    public CardStatusDTO() {

    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
}
