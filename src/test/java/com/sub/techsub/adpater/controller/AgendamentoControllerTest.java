package com.sub.techsub.adpater.controller;


import com.sub.techsub.adapter.controller.AgendamentoController;
import com.sub.techsub.adapter.controller.resources.requests.AgendamentoRequest;
import com.sub.techsub.adapter.controller.resources.responses.AgendamentoDisponibilidadeResource;
import com.sub.techsub.adapter.gateway.*;
import com.sub.techsub.core.domain.model.*;
import com.sub.techsub.core.usecase.AgendamentoUseCases;
import com.sub.techsub.core.usecase.exception.AgendamentoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringJUnitConfig
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AgendamentoControllerTest {

    @Mock
    private AgendamentoUseCases agendamentoUseCases;

    @Mock
    private EstabelecimentoGateway estabelecimentoGateway;

    @Mock
    private ProfissionalGateway profissionalGateway;

    @Mock
    private ClienteGateway clienteGateway;

    @Mock
    private AgendamentoGateway agendamentoGateway;

    @Mock
    private ServicoGateway servicoGateway;

    @InjectMocks
    private AgendamentoController agendamentoController;

    private AgendamentoRequest agendamentoRequest;

    private Estabelecimento estabelecimento;
    private Profissional profissional;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        agendamentoRequest = new AgendamentoRequest();
        agendamentoRequest.setEstabelecimento(1L);
        agendamentoRequest.setProfissional(1L);
        agendamentoRequest.setCliente(1L);
        agendamentoRequest.setDataAgendamento(LocalDateTime.of(2024, 11, 11, 10, 0));

        estabelecimento = new Estabelecimento();
        estabelecimento.setId(1L);

        profissional = new Profissional();
        profissional.setId(1L);

        cliente = new Cliente();
        cliente.setId(1L);


    }

    @Test
    void realizarAgendamento_DeveRetornarSalvarAgendamentoNoBanco() {
        when(estabelecimentoGateway.buscarEstabelecimentoPorId(1L)).thenReturn(Optional.of(estabelecimento));
        when(profissionalGateway.buscarProfissionalPorId(1L)).thenReturn(Optional.of(profissional));
        when(clienteGateway.buscarClientePorId(1L)).thenReturn(Optional.of(cliente));
        when(agendamentoUseCases.formatarDataParaAgendamento(any())).thenReturn(LocalDate.from(LocalDateTime.now()));
        when(agendamentoUseCases.formatarHoraParaAgendamento(any())).thenReturn(LocalTime.from(LocalDateTime.now()));
        when(agendamentoGateway.salvarAgendamento(any())).thenReturn(new Agendamento());
        when(profissionalGateway.buscarProfissionalPorReference(1L)).thenReturn(profissional);

        agendamentoController.realizarAgendamento(agendamentoRequest);

        verify(agendamentoGateway, times(1)).salvarAgendamento(any());
    }

    @Test
    void realizarAgendamento_EstabelecimentoNaoEncontrado_DeveLancarExcecao() {
        when(estabelecimentoGateway.buscarEstabelecimentoPorId(1L)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            agendamentoController.realizarAgendamento(agendamentoRequest);
        });
        assertEquals("No value present", exception.getMessage());
    }

    @Test
    void listarTodos_AgendamentosExistem_DeveRetornarLista() {
        Servico servico = new Servico();
        servico.setId(1L);
        servico.setNome("Manicure");

        Profissional profissional = new Profissional();
        profissional.setNome("Fulano Fiap");
        profissional.setTarifas(100.00);
        profissional.setId(1L);
        profissional.setServico(servico);

       Estabelecimento estabelecimento = new Estabelecimento();
       estabelecimento.setNome("Beleza Now");
       estabelecimento.setEndereco("Rua das Grevilhas");
       estabelecimento.setId(1L);

       Cliente cliente = new Cliente();
       cliente.setNome("Fulaninho");
       cliente.setId(1L);

        Agendamento agendamento = new Agendamento();
        agendamento.setId(1L);
        agendamento.setStatus("Agendado");
        agendamento.setProfissional(profissional);
        agendamento.setEstabelecimento(estabelecimento);
        agendamento.setCliente(cliente);

        when(agendamentoGateway.buscaTodosAgendamentos()).thenReturn(List.of(agendamento));
        var agendamentos = agendamentoController.listarTodos();

        assertFalse(agendamentos.isEmpty());
        assertEquals(1, agendamentos.size());
    }

    @Test
    void listarTodos_NaoExistemAgendamentos_DeveRetornarListaVazia() {
        when(agendamentoGateway.buscaTodosAgendamentos()).thenReturn(List.of());
        var agendamentos = agendamentoController.listarTodos();
        assertTrue(agendamentos.isEmpty());
    }

    @Test
    void verificarServicosProfissionaisDisponiveis_DeveRetornarDisponibilidade() {

        Servico servico = new Servico();
        servico.setId(1L);
        servico.setNome("Manicure");

        Profissional profissional = new Profissional();
        profissional.setNome("Fulano Fiap");
        profissional.setTarifas(100.00);
        profissional.setId(1L);
        profissional.setServico(servico);

        EstabelecimentoProfissional estabelecimentoProfissional = new EstabelecimentoProfissional();
        estabelecimentoProfissional.setProfissional(profissional);
        estabelecimentoProfissional.setProfissionalId(1L);


        when(estabelecimentoGateway.buscarEstabelecimentoPorId(1L)).thenReturn(Optional.of(estabelecimento));
        estabelecimento.setProfissionais(new ArrayList<>());
        estabelecimento.setEstabelecimentoServicos(new ArrayList<>());
        estabelecimento.getProfissionais().add(estabelecimentoProfissional);


        when(profissionalGateway.buscarProfissionalPorId(1L)).thenReturn(Optional.of(profissional));
        when(servicoGateway.buscarServicoPorReference(any())).thenReturn(new Servico());
        AgendamentoDisponibilidadeResource resource = agendamentoController.verificarServicosProfissionaisDisponiveis(1L);

        assertNotNull(resource);
        assertFalse(resource.getProfissionais().isEmpty());
    }

    @Test
    void verificarServicosProfissionaisDisponiveis_EstabelecimentoNaoEncontrado_DeveLancarExcecao() {
        when(estabelecimentoGateway.buscarEstabelecimentoPorId(1L)).thenReturn(Optional.empty());

        AgendamentoException exception = assertThrows(AgendamentoException.class, () -> {
            agendamentoController.verificarServicosProfissionaisDisponiveis(1L);
        });
        assertEquals("Estabelecimento nao encontrado, por favor, insira outro ID", exception.getMessage());
    }
}