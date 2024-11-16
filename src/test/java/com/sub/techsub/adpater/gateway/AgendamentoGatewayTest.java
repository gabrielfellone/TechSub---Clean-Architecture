package com.sub.techsub.adpater.gateway;

import com.sub.techsub.adapter.gateway.AgendamentoGateway;
import com.sub.techsub.core.domain.model.Agendamento;
import com.sub.techsub.infrastructure.repository.IAgendamentoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringJUnitConfig
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AgendamentoGatewayTest {

    @Mock
    private IAgendamentoRepository agendamentoRepository;

    @InjectMocks
    private AgendamentoGateway agendamentoGateway;

    @Test
    @Transactional
    void testSalvarAgendamento() {

        Agendamento agendamento = new Agendamento();
        agendamento.setStatus("AGENDADO");
        agendamento.setId(1L);

        when(agendamentoRepository.save(agendamento)).thenReturn(agendamento);
        Agendamento agendamentoSalvo = agendamentoGateway.salvarAgendamento(agendamento);

        assertNotNull(agendamentoSalvo);
        assertEquals(1L, agendamentoSalvo.getId());
        verify(agendamentoRepository, times(1)).save(agendamento);
    }

    @Test
    void testBuscarAgendamentoPorId() {
        Agendamento agendamento = new Agendamento();
        agendamento.setId(1L);

        when(agendamentoRepository.findById(1L)).thenReturn(Optional.of(agendamento));
        Optional<Agendamento> agendamentoEncontrado = agendamentoGateway.buscarAgendamentoPorId(1L);

        assertTrue(agendamentoEncontrado.isPresent());
        assertEquals(1L, agendamentoEncontrado.get().getId());
        verify(agendamentoRepository, times(1)).findById(1L);
    }

    @Test
    void testBuscarAgendamentoPorIdNaoEncontrado() {
        when(agendamentoRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<Agendamento> agendamentoEncontrado = agendamentoGateway.buscarAgendamentoPorId(1L);
        assertFalse(agendamentoEncontrado.isPresent());
        verify(agendamentoRepository, times(1)).findById(1L);
    }

    @Test
    void testBuscaTodosAgendamentos() {

        Agendamento agendamento1 = new Agendamento();
        Agendamento agendamento2 = new Agendamento();
        List<Agendamento> agendamentos = Arrays.asList(agendamento1, agendamento2);
        when(agendamentoRepository.findAll()).thenReturn(agendamentos);
        List<Agendamento> agendamentoEncontrado = agendamentoGateway.buscaTodosAgendamentos();

        assertEquals(2, agendamentoEncontrado.size());
        verify(agendamentoRepository, times(1)).findAll();
    }

    @Test
    void testBuscaTodosAgendamentosComoAgendado() {

        Agendamento agendamento1 = new Agendamento();
        agendamento1.setStatus("AGENDADO");

        Agendamento agendamento2 = new Agendamento();
        agendamento2.setStatus("CONCLUÍDO");

        List<Agendamento> agendamentos = Arrays.asList(agendamento1, agendamento2);

        when(agendamentoRepository.findAll()).thenReturn(agendamentos);

        List<Agendamento> agendamentosAgendados = agendamentoGateway.buscaTodosAgendamentosComoAgendado();

        assertEquals(1, agendamentosAgendados.size());
        assertEquals("AGENDADO", agendamentosAgendados.get(0).getStatus());
        verify(agendamentoRepository, times(1)).findAll();
    }

    @Test
    void testDeletarAgendamento() {
        Agendamento agendamento = new Agendamento();
        agendamento.setId(1L);
        agendamentoGateway.deletarAgendamento(agendamento);

        verify(agendamentoRepository, times(1)).delete(agendamento);
    }

    @Test
    void testFlush() {
        agendamentoGateway.flush();
        verify(agendamentoRepository, times(1)).flush();
    }
}
