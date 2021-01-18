package com.distribution.simulation.controller;

import com.distribution.simulation.service.AuthorityService;
import com.distribution.simulation.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorityController {

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private ModelMapper modelMapper;


}
