package com.sub.techsub.adpater.controller.api;

import com.sub.techsub.adapter.controller.AgendamentoController;
import com.sub.techsub.adapter.controller.api.AgendamentoAPI;
import com.sub.techsub.adapter.controller.resources.requests.AgendamentoRequest;
import com.sub.techsub.adapter.controller.resources.responses.AgendamentoDisponibilidadeResource;
import com.sub.techsub.adapter.controller.resources.responses.AgendamentosRealizados;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.CREATED;

@ExtendWith(MockitoExtension.class)
class AgendamentoAPITest {

    @Mock
    private AgendamentoController agendamentoController;

    @InjectMocks
    private AgendamentoAPI agendamentoAPI;



    @Test
    void realizarAgendamento_DeveRetornarIdCriado_QuandoRequisicaoForValida() {
        AgendamentoRequest agendamentoRequest = new AgendamentoRequest();
        Long idEsperado = 1L;
        when(agendamentoController.realizarAgendamento(any(AgendamentoRequest.class))).thenReturn(idEsperado);

        ResponseEntity<Long> resposta = agendamentoAPI.realizarAgendamento(agendamentoRequest);

        assertNotNull(resposta);
        assertEquals(CREATED, resposta.getStatusCode());
        assertEquals(idEsperado, resposta.getBody());
        verify(agendamentoController, times(1)).realizarAgendamento(any(AgendamentoRequest.class));
    }

    @Test
    void listarTodos_DeveRetornarListaDeAgendamentosRealizados() {
        List<AgendamentosRealizados> listaEsperada = List.of(new AgendamentosRealizados(), new AgendamentosRealizados());
        when(agendamentoController.listarTodos()).thenReturn(listaEsperada);

        List<AgendamentosRealizados> resultado = agendamentoAPI.listarTodos();

        assertNotNull(resultado);
        assertEquals(listaEsperada.size(), resultado.size());
        verify(agendamentoController, times(1)).listarTodos();
    }

    @Test
    void verificaServicosProfissionaisDisponiveis_DeveRetornarAgendamentoDisponibilidadeResource_QuandoIdForValido() {
        Long id = 1L;
        AgendamentoDisponibilidadeResource recursoEsperado = new AgendamentoDisponibilidadeResource();
        when(agendamentoController.verificarServicosProfissionaisDisponiveis(id)).thenReturn(recursoEsperado);

        AgendamentoDisponibilidadeResource resultado = agendamentoAPI.verificaServicosProfissionaisDisponiveis(id);

        assertNotNull(resultado);
        assertEquals(recursoEsperado, resultado);
        verify(agendamentoController, times(1)).verificarServicosProfissionaisDisponiveis(id);
    }
}

