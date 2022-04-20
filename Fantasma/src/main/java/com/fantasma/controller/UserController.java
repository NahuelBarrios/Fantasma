package com.fantasma.controller;

import com.fantasma.dtos.JwtDTO;
import com.fantasma.dtos.UserCreationDTO;
import com.fantasma.dtos.UserLoginDTO;
import com.fantasma.exceptions.FantasmaRequestException;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface UserController {

    @PostMapping("/auth/register")
    @ResponseStatus(HttpStatus.CREATED)
    JwtDTO createUser(@Valid @RequestBody UserCreationDTO userCreationDTO);

    @PostMapping("/auth/login")
    @ResponseStatus(HttpStatus.OK)
    JwtDTO userLogin(@Valid @RequestBody UserLoginDTO userLoginDTO) throws FantasmaRequestException;
}
