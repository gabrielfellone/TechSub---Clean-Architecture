package com.sub.techsub.adpater.controller.resources.response;

import com.sub.techsub.adapter.controller.resources.responses.AgendamentosRealizados;
import com.sub.techsub.core.domain.model.Profissional;
import com.sub.techsub.core.domain.model.Servico;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AgendamentosRealizadosTest {
    @Test
    void testConstructorAndGetters() {
        Profissional profissional = new Profissional();
        Servico servico = new Servico();
        String endereco = "Endereço Teste";
        String nomeEstabelecimento = "Estabelecimento Teste";
        String nomeCliente = "Cliente Teste";
        String statusAgendamento = "Status Teste";

        AgendamentosRealizados agendamentosRealizados = new AgendamentosRealizados(profissional, servico, endereco, nomeEstabelecimento, nomeCliente, statusAgendamento);

        assertNotNull(agendamentosRealizados);
        assertEquals(profissional, agendamentosRealizados.getProfissionais());
        assertEquals(servico, agendamentosRealizados.getServicos());
        assertEquals(endereco, agendamentosRealizados.getEndereco());
        assertEquals(nomeEstabelecimento, agendamentosRealizados.getNomeEstabelecimento());
        assertEquals(nomeCliente, agendamentosRealizados.getNomeCliente());
        assertEquals(statusAgendamento, agendamentosRealizados.getStatusAgendamento());
    }

    @Test
    void testSetters() {
        AgendamentosRealizados agendamentosRealizados = new AgendamentosRealizados();

        Profissional profissional = new Profissional();
        Servico servico = new Servico();
        String endereco = "Endereço Teste";
        String nomeEstabelecimento = "Estabelecimento Teste";
        String nomeCliente = "Cliente Teste";
        String statusAgendamento = "Status Teste";

        agendamentosRealizados.setProfissionais(profissional);
        agendamentosRealizados.setServicos(servico);
        agendamentosRealizados.setEndereco(endereco);
        agendamentosRealizados.setNomeEstabelecimento(nomeEstabelecimento);
        agendamentosRealizados.setNomeCliente(nomeCliente);
        agendamentosRealizados.setStatusAgendamento(statusAgendamento);

        assertEquals(profissional, agendamentosRealizados.getProfissionais());
        assertEquals(servico, agendamentosRealizados.getServicos());
        assertEquals(endereco, agendamentosRealizados.getEndereco());
        assertEquals(nomeEstabelecimento, agendamentosRealizados.getNomeEstabelecimento());
        assertEquals(nomeCliente, agendamentosRealizados.getNomeCliente());
        assertEquals(statusAgendamento, agendamentosRealizados.getStatusAgendamento());
    }

    @Test
    void testBuilder() {
        Profissional profissional = new Profissional();
        Servico servico = new Servico();
        String endereco = "Endereço Teste";
        String nomeEstabelecimento = "Estabelecimento Teste";
        String nomeCliente = "Cliente Teste";
        String statusAgendamento = "Status Teste";

        AgendamentosRealizados agendamentosRealizados = AgendamentosRealizados.builder()
                .profissionais(profissional)
                .servicos(servico)
                .endereco(endereco)
                .nomeEstabelecimento(nomeEstabelecimento)
                .nomeCliente(nomeCliente)
                .statusAgendamento(statusAgendamento)
                .build();

        assertNotNull(agendamentosRealizados);
        assertEquals(profissional, agendamentosRealizados.getProfissionais());
        assertEquals(servico, agendamentosRealizados.getServicos());
        assertEquals(endereco, agendamentosRealizados.getEndereco());
        assertEquals(nomeEstabelecimento, agendamentosRealizados.getNomeEstabelecimento());
        assertEquals(nomeCliente, agendamentosRealizados.getNomeCliente());
        assertEquals(statusAgendamento, agendamentosRealizados.getStatusAgendamento());
    }

}
