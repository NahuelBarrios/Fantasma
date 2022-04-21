package com.fantasma.controller;

import com.fantasma.dtos.JwtDTO;
import com.fantasma.dtos.UserCreationDTO;
import com.fantasma.dtos.UserLoginDTO;
import com.fantasma.exceptions.FantasmaRequestException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Users", description = "Operations related to Users")
public interface UserController {

    @Operation(
            summary = "Register a new User",
            description = "To register, this endpoint must be accessed"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Register user",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = JwtDTO.class))
                    }),
            @ApiResponse(responseCode = "400",
                    description = "The fields must not be empty",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "This email is in use",
                    content = @Content)
    })
    @PostMapping("/auth/register")
    @ResponseStatus(HttpStatus.CREATED)
    JwtDTO createUser(@Valid @RequestBody UserCreationDTO userCreationDTO);

    @Operation(
            summary = "User login",
            description = "To log in, you must access this endpoint"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "User login",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = JwtDTO.class))
                    }),
            @ApiResponse(responseCode = "400",
                    description = "The password is invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "User not found",
                    content = @Content)
    })
    @PostMapping("/auth/login")
    @ResponseStatus(HttpStatus.OK)
    JwtDTO userLogin(@Valid @RequestBody UserLoginDTO userLoginDTO) throws FantasmaRequestException;
}
