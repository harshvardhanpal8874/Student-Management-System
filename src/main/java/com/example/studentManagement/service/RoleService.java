package com.example.studentManagement.service;

import com.example.studentManagement.entity.Role;
import com.example.studentManagement.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role addRole(Role role) {
        return roleRepository.save(role);
    }
}
