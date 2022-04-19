package com.fantasma.mapper;

import com.fantasma.domain.EntidadUno;
import com.fantasma.dtos.EntidadUnoDTO;
import com.fantasma.dtos.EntidadUnoDTOCreation;
import com.fantasma.dtos.EntidadUnoDTOUpdate;
import com.fantasma.repository.models.EntidadUnoModel;

public class EntidadUnoMapper {

    public static EntidadUnoModel mapDomainToModel(EntidadUno entidadUno){
        EntidadUnoModel entidadUnoModel = EntidadUnoModel.builder()
                .id(entidadUno.getId())
                .name(entidadUno.getName())
                .fecha(entidadUno.getFecha()).build();
        return entidadUnoModel;
    }

    public static EntidadUno mapModelToDomain(EntidadUnoModel entidadUnoModel){
        EntidadUno entidadUno = EntidadUno.builder()
                .id(entidadUnoModel.getId())
                .name(entidadUnoModel.getName())
                .fecha(entidadUnoModel.getFecha()).build();
        return entidadUno;
    }

    public static EntidadUno mapCreationToDomain(EntidadUnoDTOCreation entidadUnoDTOCreation){
        EntidadUno entidadUno = EntidadUno.builder()
                .name(entidadUnoDTOCreation.getName())
                .fecha(entidadUnoDTOCreation.getFecha()).build();
        return entidadUno;
    }

    public static EntidadUnoDTO mapDomainToDTO(EntidadUno entidadUno){
        EntidadUnoDTO entidadUnoDTO = EntidadUnoDTO.builder()
                .name(entidadUno.getName())
                .fecha(entidadUno.getFecha()).build();
        return entidadUnoDTO;
    }

    public static EntidadUno mapUpdateToDomain(EntidadUnoDTOUpdate entidadUnoDTOUpdate){
        EntidadUno entidadUno = EntidadUno.builder()
                .name(entidadUnoDTOUpdate.getName())
                .fecha(entidadUnoDTOUpdate.getFecha()).build();
        return entidadUno;
    }
}
