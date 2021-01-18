package com.distribution.simulation.service.impl;

import com.distribution.simulation.repository.AuthorityRepository;
import com.distribution.simulation.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;


}
