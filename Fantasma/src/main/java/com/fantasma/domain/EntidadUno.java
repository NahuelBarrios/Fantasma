package com.fantasma.domain;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EntidadUno {
    private Long id;
    private String name;
    private Date fecha;
}
