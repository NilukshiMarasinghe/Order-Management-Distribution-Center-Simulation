package com.distribution.worker.service;


import com.distribution.worker.entity.Role;

public interface RoleService {

    Role save(Role role);

    Role view(Long id);
}
