package com.example.studentManagement.controller;

import com.example.studentManagement.dto.UserRegisterRequestDTO;
import com.example.studentManagement.dto.UserRegisterResponseDTO;
import com.example.studentManagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDTO> register(
            @Valid @RequestBody UserRegisterRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.register(dto));
    }
}
