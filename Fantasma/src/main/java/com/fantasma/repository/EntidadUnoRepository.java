package com.fantasma.repository;

import com.fantasma.repository.models.EntidadUnoModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntidadUnoRepository extends CrudRepository<EntidadUnoModel,Long> {
}
