package com.distribution.simulation.service.impl;

import com.distribution.simulation.entity.Role;
import com.distribution.simulation.exception.ComplexValidationException;
import com.distribution.simulation.repository.RoleRepository;
import com.distribution.simulation.service.AuthorityService;
import com.distribution.simulation.service.RoleService;
import com.distribution.simulation.modelmapper.ModelMapper;
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

    @Autowired
    private AuthorityService authorityService;

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
