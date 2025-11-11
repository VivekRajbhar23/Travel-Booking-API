package com.travelapi.controllers;

import com.travelapi.dto.BookingDTO;
import com.travelapi.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService service;

    @PostMapping
    public ResponseEntity<BookingDTO> create(@RequestBody BookingDTO dto){ return ResponseEntity.ok(service.create(dto)); }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> get(@PathVariable Long id){ return ResponseEntity.ok(service.getById(id)); }

    @GetMapping
    public ResponseEntity<List<BookingDTO>> all(){ return ResponseEntity.ok(service.getAll()); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){ service.delete(id); return ResponseEntity.noContent().build(); }
}
