package com.sub.techsub.adpater.controller.resources.request;

import com.sub.techsub.adapter.controller.resources.requests.AgendamentoRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
public class AgendamentoRequestTest {

    @Test
    void testConstructorAndGetters() {
        Long estabelecimento = 1L;
        Long profissional = 1L;
        Long cliente = 1L;
        String status = "Agendar";
        LocalDateTime dataAgendamento = LocalDateTime.parse("2024-09-16T14:38:19.619");

        AgendamentoRequest agendamentoRequest = new AgendamentoRequest(estabelecimento, profissional, cliente, status, dataAgendamento);

        assertNotNull(agendamentoRequest);
        assertEquals(estabelecimento, agendamentoRequest.getEstabelecimento());
        assertEquals(profissional, agendamentoRequest.getProfissional());
        assertEquals(cliente, agendamentoRequest.getCliente());
        assertEquals(status, agendamentoRequest.getStatus());
        assertEquals(dataAgendamento, agendamentoRequest.getDataAgendamento());
    }

    @Test
    void testSetters() {
        AgendamentoRequest agendamentoRequest = new AgendamentoRequest();

        Long estabelecimento = 1L;
        Long profissional = 1L;
        Long cliente = 1L;
        String status = "Agendar";
        LocalDateTime dataAgendamento = LocalDateTime.parse("2024-09-16T14:38:19.619");

        agendamentoRequest.setEstabelecimento(estabelecimento);
        agendamentoRequest.setProfissional(profissional);
        agendamentoRequest.setCliente(cliente);
        agendamentoRequest.setStatus(status);
        agendamentoRequest.setDataAgendamento(dataAgendamento);

        assertEquals(estabelecimento, agendamentoRequest.getEstabelecimento());
        assertEquals(profissional, agendamentoRequest.getProfissional());
        assertEquals(cliente, agendamentoRequest.getCliente());
        assertEquals(status, agendamentoRequest.getStatus());
        assertEquals(dataAgendamento, agendamentoRequest.getDataAgendamento());
    }

    @Test
    void testBuilder() {
        Long estabelecimento = 1L;
        Long profissional = 1L;
        Long cliente = 1L;
        String status = "Agendar";
        LocalDateTime dataAgendamento = LocalDateTime.parse("2024-09-16T14:38:19.619");

        AgendamentoRequest agendamentoRequest = AgendamentoRequest.builder()
                .estabelecimento(estabelecimento)
                .profissional(profissional)
                .cliente(cliente)
                .status(status)
                .dataAgendamento(dataAgendamento)
                .build();

        assertNotNull(agendamentoRequest);
        assertEquals(estabelecimento, agendamentoRequest.getEstabelecimento());
        assertEquals(profissional, agendamentoRequest.getProfissional());
        assertEquals(cliente, agendamentoRequest.getCliente());
        assertEquals(status, agendamentoRequest.getStatus());
        assertEquals(dataAgendamento, agendamentoRequest.getDataAgendamento());
    }
}

