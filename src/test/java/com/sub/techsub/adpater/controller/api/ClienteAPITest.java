package com.sub.techsub.adpater.controller.api;

import com.sub.techsub.adapter.controller.ClienteController;
import com.sub.techsub.adapter.controller.api.ClienteAPI;
import com.sub.techsub.adapter.controller.resources.requests.ClienteRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.CREATED;

@ExtendWith(MockitoExtension.class)
class ClienteAPITest {

    @Mock
    private ClienteController clienteController;

    @InjectMocks
    private ClienteAPI clienteAPI;


    @Test
    void registrarCliente_DeveRetornarIdCriado_QuandoRequisicaoForValida() {
        ClienteRequest clienteRequest = new ClienteRequest();
        Long idEsperado = 1L;
        when(clienteController.registrarCliente(any(ClienteRequest.class))).thenReturn(idEsperado);

        ResponseEntity<Long> resposta = clienteAPI.registrarCliente(clienteRequest);

        assertNotNull(resposta);
        assertEquals(CREATED, resposta.getStatusCode());
        assertEquals(idEsperado, resposta.getBody());
        verify(clienteController, times(1)).registrarCliente(any(ClienteRequest.class));
    }
}
