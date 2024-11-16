package com.sub.techsub.core.domain.model;

import com.sub.techsub.adapter.controller.resources.requests.ClienteRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteModelTest {

    @Test
    void testCriacaoCliente() {

        String nome = "João da Silva";

        Cliente cliente = new Cliente();
        cliente.setNome(nome);

        assertNotNull(cliente);
        assertEquals(nome, cliente.getNome());
    }

    @Test
    void testCriacaoClienteComClienteRequest() {

        ClienteRequest clienteRequest = new ClienteRequest();
        clienteRequest.setNome("Maria Oliveira");
        Cliente cliente = new Cliente(clienteRequest);
        assertNotNull(cliente);
        assertEquals("Maria Oliveira", cliente.getNome());
    }

    @Test
    void testEqualsAndHashCode() {

        String nome = "João da Silva";

        Cliente cliente1 = new Cliente();
        cliente1.setNome(nome);

        Cliente cliente2 = new Cliente();
        cliente2.setNome(nome);

        assertEquals(cliente1, cliente2);
        assertEquals(cliente1.hashCode(), cliente2.hashCode());
    }

    @Test
    void testNotEquals() {

        Cliente cliente1 = new Cliente();
        cliente1.setNome("João da Silva");
        Cliente cliente2 = new Cliente();
        cliente2.setNome("Maria Oliveira");
        assertNotEquals(cliente1, cliente2);
    }
}
