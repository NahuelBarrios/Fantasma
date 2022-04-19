package com.fantasma.controller.impl;

import com.fantasma.controller.EntidadUnoController;
import com.fantasma.domain.EntidadUno;
import com.fantasma.dtos.EntidadUnoDTO;
import com.fantasma.dtos.EntidadUnoDTOCreation;
import com.fantasma.dtos.EntidadUnoDTOUpdate;
import com.fantasma.exceptions.FantasmaRequestException;
import com.fantasma.mapper.EntidadUnoMapper;
import com.fantasma.service.EntidadUnoService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntidadUnoResource implements EntidadUnoController {

    private final EntidadUnoService entidadUnoService;

    public EntidadUnoResource(EntidadUnoService entidadUnoService){
        this.entidadUnoService = entidadUnoService;
    }

    @Override
    public EntidadUnoDTO createEntidad(EntidadUnoDTOCreation entidadUnoDTOCreation) {
        EntidadUno entidadUno = EntidadUnoMapper.mapCreationToDomain(entidadUnoDTOCreation);
        EntidadUnoDTO entidadUnoDTO = EntidadUnoMapper.mapDomainToDTO(entidadUnoService.createEntidadUno(entidadUno));
        return entidadUnoDTO;
    }

    @Override
    public EntidadUnoDTO updateEntidad(Long id ,EntidadUnoDTOUpdate entidadUnoDTOUpdate) throws FantasmaRequestException {
        EntidadUno entidadUno = EntidadUnoMapper.mapUpdateToDomain(entidadUnoDTOUpdate);
        EntidadUnoDTO entidadUnoDTO = EntidadUnoMapper.mapDomainToDTO(entidadUnoService.updateEntidadUno(id,entidadUno));
        return entidadUnoDTO;
    }
}
