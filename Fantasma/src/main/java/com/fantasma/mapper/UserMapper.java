package com.fantasma.mapper;

import com.fantasma.domain.User;
import com.fantasma.dtos.UserCreationDTO;
import com.fantasma.dtos.UserDTO;
import com.fantasma.dtos.UserLoginDTO;
import com.fantasma.repository.models.UserModel;

public class UserMapper {
    public static User mapModelToDomain(UserModel userModel) {
        User userDomain = User.builder()
                .firstName(userModel.getFirstName())
                .lastName(userModel.getLastName())
                .email(userModel.getEmail())
                .password(userModel.getPassword())
                .role(RoleMapper.mapModelToDomain(userModel.getRole())).build();
        return userDomain;
    }

    public static UserModel mapDomainToModel(User userDomain) {
        UserModel userModel = UserModel.builder()
                .firstName(userDomain.getFirstName())
                .lastName(userDomain.getLastName())
                .email(userDomain.getEmail())
                .password(userDomain.getPassword())
                .role(RoleMapper.mapDomainToModel(userDomain.getRole())).build();
        return userModel;
    }

    public static UserDTO mapDomainToDTO(User userDomain) {
        UserDTO userDTO = UserDTO.builder().
                lastName(userDomain.getLastName()).
                firstName(userDomain.getFirstName()).
                id(userDomain.getId()).
                creationDate(userDomain.getCreationDate()).
                email(userDomain.getEmail()).build();
        return userDTO;
    }

    public static User mapDtoCreationToDomain(UserCreationDTO userCreationDTO) {
        User userDomain = User.builder().
                email(userCreationDTO.getEmail()).
                firstName(userCreationDTO.getName()).
                lastName(userCreationDTO.getLastName()).
                password(userCreationDTO.getPassword()).build();
        return userDomain;
    }

    public static User mapLoginDTOToDomain(UserLoginDTO userLoginDTO) {
        User user = User.builder()
                .password(userLoginDTO.getPassword())
                .email(userLoginDTO.getEmail())
                .build();
        return user;
    }
}
