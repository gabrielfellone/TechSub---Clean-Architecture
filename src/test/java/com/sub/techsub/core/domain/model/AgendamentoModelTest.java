package com.sub.techsub.core.domain.model;


import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class AgendamentoModelTest {

    @Test
    void testCriacaoAgendamento() {

        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setId(1L);
        estabelecimento.setNome("Estabelecimento A");
        estabelecimento.setEndereco("Rua X");

        Profissional profissional = new Profissional();
        profissional.setId(1L);
        profissional.setNome("Profissional A");

        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Cliente A");

        LocalDate data = LocalDate.of(2024, 11, 10);
        LocalTime hora = LocalTime.of(15, 30);


        Agendamento agendamento = new Agendamento();
        agendamento.setId(1L);
        agendamento.setEstabelecimento(estabelecimento);
        agendamento.setProfissional(profissional);
        agendamento.setCliente(cliente);
        agendamento.setStatus("Pendente");
        agendamento.setDataAgendamento(data);
        agendamento.setHoraAgendamento(hora);


        assertNotNull(agendamento);
        assertEquals(1L, agendamento.getId());
        assertEquals("Estabelecimento A", agendamento.getEstabelecimento().getNome());
        assertEquals("Profissional A", agendamento.getProfissional().getNome());
        assertEquals("Cliente A", agendamento.getCliente().getNome());
        assertEquals(data, agendamento.getDataAgendamento());
        assertEquals(hora, agendamento.getHoraAgendamento());
    }

    @Test
    void testEqualsAndHashCode() {

        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setId(1L);
        estabelecimento.setNome("Estabelecimento A");
        estabelecimento.setEndereco("Rua X");

        Profissional profissional = new Profissional();
        profissional.setId(1L);
        profissional.setNome("Profissional A");

        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Cliente A");

        LocalDate data = LocalDate.of(2024, 11, 10);
        LocalTime hora = LocalTime.of(15, 30);

        Agendamento agendamento1 = new Agendamento();
        agendamento1.setId(1L);
        agendamento1.setEstabelecimento(estabelecimento);
        agendamento1.setProfissional(profissional);
        agendamento1.setCliente(cliente);
        agendamento1.setStatus("Pendente");
        agendamento1.setDataAgendamento(data);
        agendamento1.setHoraAgendamento(hora);

        Agendamento agendamento2 = new Agendamento();
        agendamento2.setId(1L);
        agendamento2.setEstabelecimento(estabelecimento);
        agendamento2.setProfissional(profissional);
        agendamento2.setCliente(cliente);
        agendamento2.setStatus("Pendente");
        agendamento2.setDataAgendamento(data);
        agendamento2.setHoraAgendamento(hora);

        assertEquals(agendamento1, agendamento2);
        assertEquals(agendamento1.hashCode(), agendamento2.hashCode());
    }

    @Test
    void testNotEquals() {

        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setId(1L);
        estabelecimento.setNome("Estabelecimento A");
        estabelecimento.setEndereco("Rua X");

        Profissional profissional = new Profissional();
        profissional.setId(1L);
        profissional.setNome("Profissional A");

        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Cliente A");

        Agendamento agendamento1 = new Agendamento();
        agendamento1.setId(1L);
        agendamento1.setEstabelecimento(estabelecimento);
        agendamento1.setProfissional(profissional);
        agendamento1.setCliente(cliente);
        agendamento1.setStatus("Pendente");
        agendamento1.setDataAgendamento(LocalDate.of(2024, 11, 10));
        agendamento1.setHoraAgendamento(LocalTime.of(15, 30));

        Agendamento agendamento2 = new Agendamento();
        agendamento2.setId(2L);
        agendamento2.setEstabelecimento(estabelecimento);
        agendamento2.setProfissional(profissional);
        agendamento2.setCliente(cliente);
        agendamento2.setStatus("Confirmado");
        agendamento2.setDataAgendamento(LocalDate.of(2024, 11, 11));


        assertNotEquals(agendamento1, agendamento2);
    }


    @Test
    void testAtributosObrigatorios() {

        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setId(1L);
        estabelecimento.setNome("Estabelecimento A");
        estabelecimento.setEndereco("Rua X");

        Profissional profissional = new Profissional();
        profissional.setId(1L);
        profissional.setNome("Profissional A");

        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Cliente A");


        Agendamento agendamento = new Agendamento();
        agendamento.setEstabelecimento(estabelecimento);
        agendamento.setProfissional(profissional);
        agendamento.setCliente(cliente);
        agendamento.setStatus("Pendente");
        agendamento.setDataAgendamento(LocalDate.of(2024, 11, 10));
        agendamento.setHoraAgendamento(LocalTime.of(15, 30));


        assertNotNull(agendamento.getDataAgendamento());
        assertNotNull(agendamento.getHoraAgendamento());
    }

    @Test
    void testRelacionamentoEntidades() {

        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setId(1L);
        estabelecimento.setNome("Estabelecimento A");
        estabelecimento.setEndereco("Rua X");

        Profissional profissional = new Profissional();
        profissional.setId(1L);
        profissional.setNome("Profissional A");

        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Cliente A");

        LocalDate data = LocalDate.of(2024, 11, 10);
        LocalTime hora = LocalTime.of(15, 30);

        Agendamento agendamento = new Agendamento();
        agendamento.setId(1L);
        agendamento.setEstabelecimento(estabelecimento);
        agendamento.setProfissional(profissional);
        agendamento.setCliente(cliente);
        agendamento.setStatus("Pendente");
        agendamento.setDataAgendamento(data);
        agendamento.setHoraAgendamento(hora);

        assertNotNull(agendamento.getEstabelecimento());
        assertNotNull(agendamento.getProfissional());
        assertNotNull(agendamento.getCliente());
    }
}
