package com.travelapi.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDTO {
    private Long id;
    private Long bookingId;
    private Double amount;
    private String method;
    private String status;
    private LocalDateTime paidAt;
}
