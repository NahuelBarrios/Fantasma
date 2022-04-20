package com.fantasma.repository;

import com.fantasma.repository.models.RoleModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<RoleModel,Long> {
    RoleModel findByName(String name);
}
