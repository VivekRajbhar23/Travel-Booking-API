package com.travelapi.service.impl;

import com.travelapi.dto.UserDTO;
import com.travelapi.entity.User;
import com.travelapi.exception.ResourceNotFoundException;
import com.travelapi.repository.UserRepository;
import com.travelapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private UserDTO toDto(User u){
        return UserDTO.builder()
                .id(u.getId())
                .name(u.getName())
                .email(u.getEmail())
                .phone(u.getPhone())
                .build();
    }

    private User toEntity(UserDTO dto){
        return User.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .build();
    }

    @Override
    public UserDTO create(UserDTO user) {
        User saved = userRepository.save(toEntity(user));
        return toDto(saved);
    }

    @Override
    public UserDTO getById(Long id) {
        User u = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));
        return toDto(u);
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDTO update(Long id, UserDTO user) {
        User exist = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));
        exist.setName(user.getName());
        exist.setEmail(user.getEmail());
        exist.setPhone(user.getPhone());
        return toDto(userRepository.save(exist));
    }

    @Override
    public void delete(Long id) {
        userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));
        userRepository.deleteById(id);
    }
}
