package com.sub.techsub.adpater.controller;

import com.sub.techsub.adapter.controller.ClienteController;
import com.sub.techsub.adapter.controller.resources.requests.ClienteRequest;
import com.sub.techsub.adapter.gateway.ClienteGateway;
import com.sub.techsub.core.domain.model.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringJUnitConfig
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class ClienteControllerTest {


    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private ClienteGateway clienteGateway;

    private ClienteRequest clienteRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        clienteRequest = new ClienteRequest();
        clienteRequest.setNome("João da Silva");
;
    }

    @Test
    void deveRegistrarClienteComSucesso() {
        Cliente cliente = new Cliente(clienteRequest);
        cliente.setId(1L);

        when(clienteGateway.salvarCliente(any(Cliente.class))).thenReturn(cliente);

        Long clienteId = clienteController.registrarCliente(clienteRequest);

        assertNotNull(clienteId);
        assertEquals(1L, clienteId);
        verify(clienteGateway, times(1)).salvarCliente(any(Cliente.class));
    }


}
