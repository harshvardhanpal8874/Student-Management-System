package com.example.studentManagement.controller;

import com.example.studentManagement.dto.CreateStudentRequestDTO;
import com.example.studentManagement.dto.StudentResponseDTO;
import com.example.studentManagement.dto.UpdateStudentDTO;
import com.example.studentManagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<StudentResponseDTO> create(
            @Valid @RequestBody CreateStudentRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.createStudent(dto));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<StudentResponseDTO> get(@PathVariable long id) {
        return ResponseEntity.ok(studentService.findStudentById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StudentResponseDTO> update(
            @PathVariable long id,
            @Valid @RequestBody UpdateStudentDTO dto) {
        return ResponseEntity.ok(studentService.updateStudent(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<StudentResponseDTO>> getAll() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/search")
    public ResponseEntity<List<StudentResponseDTO>> search(
            @RequestParam String name) {
        return ResponseEntity.ok(studentService.searchByName(name));
    }
}
