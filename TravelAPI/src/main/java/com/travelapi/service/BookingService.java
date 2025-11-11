package com.travelapi.service;

import com.travelapi.dto.BookingDTO;
import java.util.List;

public interface BookingService {
    BookingDTO create(BookingDTO dto);
    BookingDTO getById(Long id);
    List<BookingDTO> getAll();
    void delete(Long id);
}
