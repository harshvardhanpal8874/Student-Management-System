package com.example.studentManagement.service;

import com.example.studentManagement.dto.UserRegisterRequestDTO;
import com.example.studentManagement.dto.UserRegisterResponseDTO;
import com.example.studentManagement.entity.Role;
import com.example.studentManagement.entity.User;
import com.example.studentManagement.repository.RoleRepository;
import com.example.studentManagement.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserRegisterResponseDTO register(UserRegisterRequestDTO dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        Role role = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new IllegalStateException("ROLE_USER does not exist"));

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.getRoles().add(role);

        User saved = userRepository.save(user);

        return new UserRegisterResponseDTO(
                saved.getId(),
                saved.getUsername(),
                "Registration successful"
        );
    }
}
