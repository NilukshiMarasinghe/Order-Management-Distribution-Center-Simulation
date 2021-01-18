package com.distribution.simulation.service;

import com.distribution.simulation.entity.Role;

public interface RoleService {

    Role save(Role role);

    Role view(Long id);
}
