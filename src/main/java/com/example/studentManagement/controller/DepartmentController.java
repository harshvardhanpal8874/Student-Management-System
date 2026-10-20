package com.example.studentManagement.controller;

import com.example.studentManagement.entity.Department;
import com.example.studentManagement.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {

        this.departmentService = departmentService;
    }

    @PostMapping("/add")
    public ResponseEntity<Department> add(@RequestBody Department department) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(departmentService.saveDepartment(department));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Department>> getAll() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }
}
