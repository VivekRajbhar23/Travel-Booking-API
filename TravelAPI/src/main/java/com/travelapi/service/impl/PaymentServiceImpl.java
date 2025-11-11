package com.travelapi.service.impl;

import com.travelapi.dto.PaymentDTO;
import com.travelapi.entity.Booking;
import com.travelapi.entity.Payment;
import com.travelapi.exception.ResourceNotFoundException;
import com.travelapi.repository.BookingRepository;
import com.travelapi.repository.PaymentRepository;
import com.travelapi.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepo;
    private final BookingRepository bookingRepo;

    private PaymentDTO toDto(Payment p){
        return PaymentDTO.builder()
                .id(p.getId())
                .bookingId(p.getBooking() != null ? p.getBooking().getId() : null)
                .amount(p.getAmount())
                .method(p.getMethod())
                .status(p.getStatus())
                .paidAt(p.getPaidAt())
                .build();
    }

    @Override
    public PaymentDTO create(PaymentDTO dto) {
        Booking booking = bookingRepo.findById(dto.getBookingId()).orElseThrow(() -> new ResourceNotFoundException("Booking not found: " + dto.getBookingId()));
        Payment p = Payment.builder()
                .booking(booking)
                .amount(dto.getAmount())
                .method(dto.getMethod())
                .status(dto.getStatus() == null ? "PENDING" : dto.getStatus())
                .paidAt(dto.getPaidAt() != null ? dto.getPaidAt() : LocalDateTime.now())
                .build();
        return toDto(paymentRepo.save(p));
    }

    @Override
    public PaymentDTO getById(Long id) {
        return toDto(paymentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Payment not found: " + id)));
    }

    @Override
    public List<PaymentDTO> getAll() {
        return paymentRepo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }
}
