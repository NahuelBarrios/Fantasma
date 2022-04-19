package com.fantasma.dtos;

import com.fantasma.domain.EntidadUno;
import com.fantasma.mapper.EntidadUnoMapper;
import com.fantasma.service.EntidadUnoService;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EntidadUnoListDTO {
    private List<EntidadUnoDTO> entidadUnoDTOS;
    private String nextPageUrl;
    private String previousPageUrl;

    @Autowired
    EntidadUnoService entidadUnoService;

    public EntidadUnoListDTO(Integer page, Page<EntidadUno> entidadUnos, String currentContextPath) {
        if (entidadUnos.hasPrevious()) {
            this.previousPageUrl = currentContextPath.concat(String.format("/members?page=%d", page - 1));
        }
        if (entidadUnos.hasNext()) {
            this.nextPageUrl = currentContextPath.concat(String.format("/members?page=%d", page + 1));
        }
        this.entidadUnoDTOS = entidadUnos.getContent().stream().map(EntidadUnoMapper::mapDomainToDTO).collect(Collectors.toList());
    }
}
