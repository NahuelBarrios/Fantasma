package com.fantasma.dtos;

import java.io.Serializable;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EntidadUnoDTO implements Serializable {
    private String name;
    private Date fecha;
}
