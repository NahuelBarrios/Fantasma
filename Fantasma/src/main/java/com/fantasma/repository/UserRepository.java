package com.fantasma.repository;

import com.fantasma.repository.models.UserModel;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserModel,Long> {
    UserModel findByEmail(String email);
    boolean existsByEmail(String email);
    //List<UserModel> findAll();
}
