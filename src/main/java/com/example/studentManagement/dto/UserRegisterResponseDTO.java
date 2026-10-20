package com.example.studentManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRegisterResponseDTO {
    private Long id;
    private String username;
    private String message;
}
