package com.api.thuctaptotnghiepbackend.Request;


import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Builder
public class RefundRequest {
    
    @NotBlank(message = "Payment ID is required")
    private String paymentId;
    
    @NotNull(message = "Refund amount cannot be null")
    @DecimalMin(value = "0.01", message = "Refund amount must be greater than 0")
    private BigDecimal refundAmount;
    
    @NotBlank(message = "Currency is required")
    private String currency;

    // Getters and setters
    // Constructors
    // Any additional methods
}

