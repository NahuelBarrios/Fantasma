package com.fantasma.controller.impl;

import com.fantasma.controller.UserController;
import com.fantasma.domain.User;
import com.fantasma.dtos.JwtDTO;
import com.fantasma.dtos.UserCreationDTO;
import com.fantasma.dtos.UserLoginDTO;
import com.fantasma.exceptions.FantasmaRequestException;
import com.fantasma.mapper.UserMapper;
import com.fantasma.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "UserResource", tags = {"Users"})
@RestController
public class UserResource implements UserController {

    private final UserService userService;

    public UserResource(UserService userService){
        this.userService = userService;
    }

    @Override
    public JwtDTO createUser(UserCreationDTO userCreationDTO) {
        User userDomain = UserMapper.mapDtoCreationToDomain(userCreationDTO);
        userService.registerUser(userDomain);
        JwtDTO jwtDto = userService.generateAuthenticationToken(userDomain);
        return jwtDto;
    }

    @Override
    public JwtDTO userLogin(UserLoginDTO userLoginDTO) throws FantasmaRequestException {
        User userDomain = UserMapper.mapLoginDTOToDomain(userLoginDTO);
        UserMapper.mapDomainToDTO(userService.loginUser(userDomain));
        JwtDTO jwtDto = userService.generateAuthenticationToken(userDomain);
        return jwtDto;
    }


}
