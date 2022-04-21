package com.fantasma.controller;

import com.fantasma.dtos.EntidadUnoDTO;
import com.fantasma.dtos.EntidadUnoDTOCreation;
import com.fantasma.dtos.EntidadUnoDTOUpdate;
import com.fantasma.dtos.EntidadUnoListDTO;
import com.fantasma.exceptions.FantasmaRequestException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Entidades", description = "Operations related to EntidadUno")
public interface EntidadUnoController {

    @Operation(
            summary = "Create new EntidadUno",
            description = "To create a entidadUno, you must access this endpoint.")
    @PostMapping("/entidades")
    @ResponseStatus(HttpStatus.CREATED)
    EntidadUnoDTO createEntidad(@Valid @RequestBody EntidadUnoDTOCreation entidadUnoDTOCreation);

    @Operation(summary = "Update a entidadUno by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update a entidadUno by id",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EntidadUnoDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "EntidadUno not found", content = @Content)})
    @PutMapping("/entidades/{id}")
    @ResponseStatus(HttpStatus.OK)
    EntidadUnoDTO updateEntidad(@PathVariable Long id, @Valid @RequestBody EntidadUnoDTOUpdate entidadUnoDTOUpdate) throws FantasmaRequestException;

    @Operation(
            summary = "Get EntidadesUno list",
            description = "To get a paginated list of the fantasma EntidadUno, you must access this endpoint.")
    @GetMapping("/entidades")
    @ResponseStatus(HttpStatus.OK)
    EntidadUnoListDTO findAll(@RequestParam(defaultValue = "0") Integer page);

    @Operation(summary = "Delete a EntidadUno by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete a entidadUno by id"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "EntidadUno not found", content = @Content)})
    @DeleteMapping("/entidades/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteEntidad(@PathVariable Long id) throws FantasmaRequestException;
}
