package com.distribution.simulation.service;

import com.distribution.simulation.entity.User;

public interface UserService {

    User save(User user);

    User view(Long id);
}
