package com.travelapi.service.impl;

import com.travelapi.dto.BookingDTO;
import com.travelapi.entity.Booking;
import com.travelapi.entity.Destination;
import com.travelapi.entity.User;
import com.travelapi.exception.ResourceNotFoundException;
import com.travelapi.repository.BookingRepository;
import com.travelapi.repository.DestinationRepository;
import com.travelapi.repository.UserRepository;
import com.travelapi.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepo;
    private final UserRepository userRepo;
    private final DestinationRepository destRepo;

    private BookingDTO toDto(Booking b){
        return BookingDTO.builder()
                .id(b.getId())
                .userId(b.getUser().getId())
                .destinationId(b.getDestination().getId())
                .guests(b.getGuests())
                .fromDate(b.getFromDate())
                .toDate(b.getToDate())
                .totalAmount(b.getTotalAmount())
                .build();
    }

    @Override
    public BookingDTO create(BookingDTO dto) {
        User user = userRepo.findById(dto.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found: " + dto.getUserId()));
        Destination dest = destRepo.findById(dto.getDestinationId()).orElseThrow(() -> new ResourceNotFoundException("Destination not found: " + dto.getDestinationId()));
        Booking b = Booking.builder()
                .user(user)
                .destination(dest)
                .guests(dto.getGuests())
                .fromDate(dto.getFromDate())
                .toDate(dto.getToDate())
                .totalAmount(dest.getPricePerPerson() * (dto.getGuests() == null ? 1 : dto.getGuests()))
                .build();
        return toDto(bookingRepo.save(b));
    }

    @Override
    public BookingDTO getById(Long id) {
        return toDto(bookingRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Booking not found: " + id)));
    }

    @Override
    public List<BookingDTO> getAll() {
        return bookingRepo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        bookingRepo.delete(bookingRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Booking not found: " + id)));
    }
}
