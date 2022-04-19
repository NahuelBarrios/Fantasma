package com.fantasma.controller;

import com.fantasma.dtos.EntidadUnoDTO;
import com.fantasma.dtos.EntidadUnoDTOCreation;
import com.fantasma.dtos.EntidadUnoDTOUpdate;
import com.fantasma.dtos.EntidadUnoListDTO;
import com.fantasma.exceptions.FantasmaRequestException;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface EntidadUnoController {

    @PostMapping("/entidades")
    @ResponseStatus(HttpStatus.CREATED)
    EntidadUnoDTO createEntidad(@Valid @RequestBody EntidadUnoDTOCreation entidadUnoDTOCreation);

    @PutMapping("/entidades/{id}")
    @ResponseStatus(HttpStatus.OK)
    EntidadUnoDTO updateEntidad(@PathVariable Long id, @Valid @RequestBody EntidadUnoDTOUpdate entidadUnoDTOUpdate) throws FantasmaRequestException;

    @GetMapping("/entidades")
    @ResponseStatus(HttpStatus.OK)
    EntidadUnoListDTO findAll(@RequestParam(defaultValue = "0") Integer page);

    @DeleteMapping("/entidades/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteEntidad(@PathVariable Long id) throws FantasmaRequestException;
}
