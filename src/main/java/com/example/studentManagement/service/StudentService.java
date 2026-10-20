package com.example.studentManagement.service;

import com.example.studentManagement.dto.CreateStudentRequestDTO;
import com.example.studentManagement.dto.StudentResponseDTO;
import com.example.studentManagement.dto.UpdateStudentDTO;
import com.example.studentManagement.entity.Department;
import com.example.studentManagement.entity.Student;
import com.example.studentManagement.exception.DepartmentNotFoundException;
import com.example.studentManagement.exception.DuplicateStudentException;
import com.example.studentManagement.exception.StudentNotFoundException;
import com.example.studentManagement.repository.DepartmentRepository;
import com.example.studentManagement.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;

    public StudentService(StudentRepository studentRepository,
                          DepartmentRepository departmentRepository) {
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
    }

    public StudentResponseDTO createStudent(CreateStudentRequestDTO dto) {
        if (studentRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new DuplicateStudentException("Email already exists");
        }

        if (studentRepository.findByRollNumber(dto.getRollNumber()).isPresent()) {
            throw new DuplicateStudentException("Roll number already exists");
        }

        Department department = getDepartment(dto.getDepartmentId());

        Student student = new Student();
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setEmail(dto.getEmail());
        student.setRollNumber(dto.getRollNumber());
        student.setSubject(dto.getSubject());
        student.setDepartment(department);

        return StudentResponseDTO.fromStudent(studentRepository.save(student));
    }

    public StudentResponseDTO findStudentById(long id) {
        return StudentResponseDTO.fromStudent(
                studentRepository.findById(id)
                        .orElseThrow(() ->
                                new StudentNotFoundException("Student with this id does not exist"))
        );
    }

    public StudentResponseDTO updateStudent(long id, UpdateStudentDTO dto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException("Student with this id does not exist"));

        studentRepository.findByEmail(dto.getEmail()).ifPresent(existing -> {
            if (!existing.getId().equals(id)) {
                throw new DuplicateStudentException("Email already exists");
            }
        });

        studentRepository.findByRollNumber(dto.getRollNumber()).ifPresent(existing -> {
            if (!existing.getId().equals(id)) {
                throw new DuplicateStudentException("Roll number already exists");
            }
        });

        Department department = getDepartment(dto.getDepartmentId());

        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setEmail(dto.getEmail());
        student.setRollNumber(dto.getRollNumber());
        student.setSubject(dto.getSubject());
        student.setDepartment(department);

        return StudentResponseDTO.fromStudent(studentRepository.save(student));
    }

    public void deleteStudent(long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException("Student with this id does not exist");
        }
        studentRepository.deleteById(id);
    }

    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(StudentResponseDTO::fromStudent)
                .toList();
    }

    public List<StudentResponseDTO> searchByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(StudentResponseDTO::fromStudent)
                .toList();
    }

    private Department getDepartment(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() ->
                        new DepartmentNotFoundException("Department with this id does not exist"));
    }
}
