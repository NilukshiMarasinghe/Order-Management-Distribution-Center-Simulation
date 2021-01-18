package com.distribution.worker.service.impl;


import com.distribution.worker.entity.Role;
import com.distribution.worker.exception.ComplexValidationException;
import com.distribution.worker.modelmapper.ModelMapper;
import com.distribution.worker.repository.RoleRepository;
import com.distribution.worker.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Role save(Role role) {
        try {
            return this.roleRepository.save(role);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ComplexValidationException("role", "save.PersistIssue");
        }
    }

    @Override
    public Role view(Long id) {
        Optional<Role> exRole = this.roleRepository.findById(id);
        if (!exRole.isPresent()) {
            throw new ComplexValidationException("role", "view.roleNotExist");
        }
        return exRole.get();
    }
}
