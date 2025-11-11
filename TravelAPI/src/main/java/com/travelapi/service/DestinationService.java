package com.travelapi.service;

import com.travelapi.entity.Destination;
import java.util.List;

public interface DestinationService {
    Destination create(Destination d);
    Destination getById(Long id);
    List<Destination> getAll();
    Destination update(Long id, Destination d);
    void delete(Long id);
}
