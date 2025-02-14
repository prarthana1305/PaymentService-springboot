package com.example.PaymentService.model;


import lombok.Data;

@Data
public class PaymentRequest {
    private Long userId;
    private boolean metroCard;
    private boolean peakHour;

    public PaymentRequest(Long userId, boolean metroCard, boolean peakHour, int durationInMinutes) {
        this.userId = userId;
        this.metroCard = metroCard;
        this.peakHour = peakHour;
        this.durationInMinutes = durationInMinutes;
    }

    public PaymentRequest() {

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isMetroCard() {
        return metroCard;
    }

    public void setMetroCard(boolean metroCard) {
        this.metroCard = metroCard;
    }

    public boolean isPeakHour() {
        return peakHour;
    }

    public void setPeakHour(boolean peakHour) {
        this.peakHour = peakHour;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    private int durationInMinutes;
    // Additional fields if needed
}
