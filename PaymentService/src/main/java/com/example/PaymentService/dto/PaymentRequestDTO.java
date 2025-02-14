package com.example.PaymentService.dto;


import lombok.Data;

@Data
public class PaymentRequestDTO {
    // Removed userId since it is provided as a path variable.
    private boolean peakHour;

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

    public PaymentRequestDTO(boolean peakHour, int durationInMinutes) {
        this.peakHour = peakHour;
        this.durationInMinutes = durationInMinutes;
    }

    public PaymentRequestDTO() {

    }

    private int durationInMinutes;
}
