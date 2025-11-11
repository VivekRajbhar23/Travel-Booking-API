package com.travelapi.controllers;

import com.travelapi.dto.PaymentDTO;
import com.travelapi.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService service;

    @PostMapping
    public ResponseEntity<PaymentDTO> create(@RequestBody PaymentDTO dto)
    { 
        return ResponseEntity.ok(service.create(dto)); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> get(@PathVariable Long id)
    { 
        return ResponseEntity.ok(service.getById(id));
     }

    @GetMapping
    public ResponseEntity<List<PaymentDTO>> all()
    { 
        return ResponseEntity.ok(service.getAll());
    }
}
