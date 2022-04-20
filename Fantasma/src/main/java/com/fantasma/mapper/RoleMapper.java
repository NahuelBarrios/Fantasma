package com.fantasma.mapper;

import com.fantasma.domain.Role;
import com.fantasma.repository.models.RoleModel;
import com.fantasma.util.RoleName;

public class RoleMapper {
    public static RoleModel mapDomainToModel(Role role){
        RoleModel roleModel = RoleModel.builder()
                .id(role.getId())
                .name(role.getName().toString())
                .description(role.getDescription())
                .creationDate(role.getCreationDate()).build();
        return roleModel;
    }

    public static Role mapModelToDomain(RoleModel roleModel){
        Role role = Role.builder()
                .id(roleModel.getId())
                .name(RoleName.valueOf(roleModel.getName())) // Obtengo el valor en string
                .description(roleModel.getDescription())
                .creationDate(roleModel.getCreationDate()).build();
        return role;
    }
}
