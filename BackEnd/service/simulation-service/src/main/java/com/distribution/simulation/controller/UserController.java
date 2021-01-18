package com.distribution.simulation.controller;


import com.distribution.simulation.dto.request.UserCreateIn;
import com.distribution.simulation.dto.response.UserView;

import com.distribution.simulation.dto.response.wrapper.SimpleResponseWrapper;
import com.distribution.simulation.entity.User;

import com.distribution.simulation.service.UserService;
import com.distribution.simulation.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.logging.Logger;


@RestController
public class UserController {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    private static final Logger logger = Logger.getLogger(UserController.class.getName());


    @PostMapping("${app.endpoint.userCreate}")
    public ResponseEntity<SimpleResponseWrapper<UserView>> userCreate(@Validated @RequestBody UserCreateIn in) {
        User user = this.modelMapper.map(in, User.class);
        User update = this.userService.save(user);
        UserView response = modelMapper.map(update, UserView.class);
        return new ResponseEntity<SimpleResponseWrapper<UserView>>(
                new SimpleResponseWrapper<UserView>(response), HttpStatus.CREATED);
    }

    @GetMapping("${app.endpoint.userView}")
    public ResponseEntity<SimpleResponseWrapper<UserView>> userView(@PathVariable Long id) {
        User userView = this.userService.view(id);
        UserView response = modelMapper.map(userView, UserView.class);
        return new ResponseEntity<SimpleResponseWrapper<UserView>>(
                new SimpleResponseWrapper<UserView>(response), HttpStatus.CREATED);
    }


}
