package com.travelapi.service;

import com.travelapi.dto.PaymentDTO;
import java.util.List;

public interface PaymentService {
    PaymentDTO create(PaymentDTO dto);
    PaymentDTO getById(Long id);
    List<PaymentDTO> getAll();
}
