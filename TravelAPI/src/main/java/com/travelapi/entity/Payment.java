package com.travelapi.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    private Double amount;
    private String method; // e.g., "CARD", "UPI"
    private String status; // e.g., "PENDING", "SUCCESS", "FAILED"
    private LocalDateTime paidAt;
}
