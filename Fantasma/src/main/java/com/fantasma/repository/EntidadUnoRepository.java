package com.fantasma.repository;

import com.fantasma.repository.models.EntidadUnoModel;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntidadUnoRepository extends CrudRepository<EntidadUnoModel,Long> {
    List<EntidadUnoModel> findAll();
}
