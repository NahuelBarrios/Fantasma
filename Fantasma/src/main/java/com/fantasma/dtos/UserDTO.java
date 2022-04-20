package com.fantasma.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO implements Serializable {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    @JsonIgnore
    private String password;
    private LocalDateTime creationDate;
}
