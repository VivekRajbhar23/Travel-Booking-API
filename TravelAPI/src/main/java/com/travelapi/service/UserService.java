package com.travelapi.service;

import com.travelapi.dto.UserDTO;
import java.util.List;

public interface UserService {
    UserDTO create(UserDTO user);
    UserDTO getById(Long id);
    List<UserDTO> getAll();
    UserDTO update(Long id, UserDTO user);
    void delete(Long id);
}
