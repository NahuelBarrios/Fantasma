package com.fantasma;

import com.fantasma.dtos.EntidadUnoDTO;
import com.fantasma.dtos.EntidadUnoDTOCreation;
import com.fantasma.exceptions.ApiErrorDTO;
import com.fantasma.repository.EntidadUnoRepository;
import com.fantasma.util.HeaderBuilder;
import java.util.Date;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class EntidadUnoControllerFuncionalTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    private String entidadUnoControllerUrl;
    private HttpEntity<?> entity;

    @Autowired
    EntidadUnoRepository entidadUnoRepository;

    @BeforeEach
    void setUp() {
        entidadUnoControllerUrl = testRestTemplate.getRootUri() + "/entidades";
    }

    @AfterEach
    void deleteAll() {
        entidadUnoRepository.deleteAll();
    }

    @Test
    void testCreate_returnsResponseEntidadUnoDTO() {
        //Given
        EntidadUnoDTOCreation entidadUnoDTOCreation = EntidadUnoDTOCreation.builder()
                .name("barrios")
                .fecha(new Date(2020,10,10))
                .build();
        String endpointUrl = entidadUnoControllerUrl;
        HttpHeaders headers = new HeaderBuilder()
                .withValidToken("admin@gmail.com", 3600L)
                .build();
        entity = new HttpEntity(entidadUnoDTOCreation, headers);
        //When
        ResponseEntity<EntidadUnoDTO> response = testRestTemplate.exchange(
                endpointUrl,
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<>() {
                },
                Map.of()
        );
        //Then
        assertEquals(201, response.getStatusCode().value());
    }

    @Test
    void testCreate_returnsResponseApiErrorDTO() {
        //Given
        EntidadUnoDTOCreation entidadUnoDTOCreation = EntidadUnoDTOCreation.builder()
                .name("")
                .fecha(new Date(2020,10,10))
                .build();
        String endpointUrl = entidadUnoControllerUrl;
        HttpHeaders headers = new HeaderBuilder()
                .withValidToken("admin@gmail.com", 3600L)
                .build();
        entity = new HttpEntity(entidadUnoDTOCreation, headers);
        //When
        ResponseEntity<ApiErrorDTO> response = testRestTemplate.exchange(
                endpointUrl,
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<>() {
                },
                Map.of()
        );
        //Then
        assertEquals(400, response.getStatusCode().value());
    }





}
