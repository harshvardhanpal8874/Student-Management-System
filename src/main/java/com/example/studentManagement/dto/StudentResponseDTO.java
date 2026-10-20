package com.example.studentManagement.dto;

import com.example.studentManagement.entity.Student;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponseDTO {

    private Long id;
    private String name;
    private Integer age;
    private String email;
    private Integer rollNumber;
    private String subject;
    private Long departmentId;
    private String departmentName;

    public static StudentResponseDTO fromStudent(Student student) {
        StudentResponseDTO dto = new StudentResponseDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setAge(student.getAge());
        dto.setEmail(student.getEmail());
        dto.setRollNumber(student.getRollNumber());
        dto.setSubject(student.getSubject());

        if (student.getDepartment() != null) {
            dto.setDepartmentId(student.getDepartment().getId());
            dto.setDepartmentName(student.getDepartment().getName());
        }

        return dto;
    }
}
