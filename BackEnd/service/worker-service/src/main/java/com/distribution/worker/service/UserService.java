package com.distribution.worker.service;


import com.distribution.worker.entity.User;

public interface UserService {

    User save(User user);

    User view(Long id);
}
