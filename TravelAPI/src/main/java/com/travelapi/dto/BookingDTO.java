package com.travelapi.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDTO {
    private Long id;
    private Long userId;
    private Long destinationId;
    private Integer guests;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Double totalAmount;
}
