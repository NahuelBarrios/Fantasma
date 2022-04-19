package com.fantasma.dtos;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EntidadUnoDTOCreation implements Serializable {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    private Date fecha;
}
