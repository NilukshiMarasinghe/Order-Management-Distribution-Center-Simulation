package com.distribution.simulation.controller;

import com.distribution.simulation.dto.request.RoleCreateIn;
import com.distribution.simulation.dto.response.RoleView;
import com.distribution.simulation.dto.response.wrapper.SimpleResponseWrapper;
import com.distribution.simulation.entity.Role;

import com.distribution.simulation.service.AuthorityService;
import com.distribution.simulation.service.CryptoService;
import com.distribution.simulation.service.RoleService;
import com.distribution.simulation.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleController {

    @Autowired
    private CryptoService cryptoService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleService roleService;


    @Autowired
    private AuthorityService authorityService;



    @PostMapping("${app.endpoint.roleCreate}")
    public ResponseEntity<SimpleResponseWrapper<RoleView>> roleCreate(@Validated @RequestBody RoleCreateIn in) {
        Role role = this.modelMapper.map(in, Role.class);
        Role update = this.roleService.save(role);
        RoleView response = modelMapper.map(update, RoleView.class);
        return new ResponseEntity<SimpleResponseWrapper<RoleView>>(
                new SimpleResponseWrapper<RoleView>(response), HttpStatus.CREATED);
    }



    @GetMapping("${app.endpoint.roleView}")
    public ResponseEntity<SimpleResponseWrapper<RoleView>> roleView(@PathVariable Long id) {
        Role roleView = this.roleService.view(id);
        RoleView response = modelMapper.map(roleView, RoleView.class);
        return new ResponseEntity<SimpleResponseWrapper<RoleView>>(
                new SimpleResponseWrapper<RoleView>(response), HttpStatus.CREATED);
    }

}
