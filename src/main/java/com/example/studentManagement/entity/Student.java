package com.example.studentManagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private Integer rollNumber;

    @Column(nullable = false)
    private String subject;

    @ManyToOne
    @JoinColumn(name = "dept_id", nullable = false)
    private Department department;
}
