package com.example.studentManagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStudentDTO {

    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotNull(message = "Age is required")
    @Min(value = 1, message = "Age must be at least 1")
    private Integer age;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Enter a valid email")
    private String email;

    @NotNull(message = "Roll number is required")
    @Min(value = 1, message = "Roll number must be at least 1")
    private Integer rollNumber;

    @NotBlank(message = "Subject must not be blank")
    private String subject;

    @NotNull(message = "Department id is required")
    private Long departmentId;
}
