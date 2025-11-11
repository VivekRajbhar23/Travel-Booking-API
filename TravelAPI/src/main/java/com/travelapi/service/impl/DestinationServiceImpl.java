package com.travelapi.service.impl;

import com.travelapi.entity.Destination;
import com.travelapi.exception.ResourceNotFoundException;
import com.travelapi.repository.DestinationRepository;
import com.travelapi.service.DestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DestinationServiceImpl implements DestinationService {
    private final DestinationRepository repo;

    @Override
    public Destination create(Destination d) { return repo.save(d); }
    @Override
    public Destination getById(Long id) { return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Destination not found: " + id)); }
    @Override
    public List<Destination> getAll() { return repo.findAll(); }
    @Override
    public Destination update(Long id, Destination d) {
        Destination ex = getById(id);
        ex.setName(d.getName());
        ex.setCountry(d.getCountry());
        ex.setDescription(d.getDescription());
        ex.setPricePerPerson(d.getPricePerPerson());
        return repo.save(ex);
    }
    @Override
    public void delete(Long id) { repo.delete(getById(id)); }
}
