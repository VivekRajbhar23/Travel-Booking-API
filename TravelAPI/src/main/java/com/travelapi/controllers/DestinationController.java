package com.travelapi.controllers;

import com.travelapi.entity.Destination;
import com.travelapi.service.DestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/destinations")
@RequiredArgsConstructor
public class DestinationController {
    private final DestinationService service;

    @PostMapping
    public ResponseEntity<Destination> create(@RequestBody Destination d){ return ResponseEntity.ok(service.create(d)); }

    @GetMapping("/{id}")
    public ResponseEntity<Destination> get(@PathVariable Long id){ return ResponseEntity.ok(service.getById(id)); }

    @GetMapping
    public ResponseEntity<List<Destination>> all(){ return ResponseEntity.ok(service.getAll()); }

    @PutMapping("/{id}")
    public ResponseEntity<Destination> update(@PathVariable Long id, @RequestBody Destination d){ return ResponseEntity.ok(service.update(id, d)); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){ service.delete(id); return ResponseEntity.noContent().build(); }
}
