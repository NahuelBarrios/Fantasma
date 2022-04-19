package com.fantasma.repository;

import com.fantasma.repository.models.EntidadUnoModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntidadUnoRepository extends JpaRepository<EntidadUnoModel,Long> {
}
