package com.sub.techsub.adpater.controller.resources.request;

import com.sub.techsub.adapter.controller.resources.requests.ClienteRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
public class ClienteRequestTest {

    @Test
    void testConstructorAndGetters() {
        String nome = "Gabriel Petruf";

        ClienteRequest clienteRequest = new ClienteRequest(nome);

        assertNotNull(clienteRequest);
        assertEquals(nome, clienteRequest.getNome());
    }

    @Test
    void testSetters() {
        ClienteRequest clienteRequest = new ClienteRequest();

        String nome = "Gabriel Petruf";

        clienteRequest.setNome(nome);

        assertEquals(nome, clienteRequest.getNome());
    }

    @Test
    void testBuilder() {
        String nome = "Gabriel Petruf";

        ClienteRequest clienteRequest = ClienteRequest.builder()
                .nome(nome)
                .build();

        assertNotNull(clienteRequest);
        assertEquals(nome, clienteRequest.getNome());
    }
}

