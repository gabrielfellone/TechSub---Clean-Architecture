package com.sub.techsub.adpater.controller.api;

import com.sub.techsub.adapter.controller.EstabelecimentoController;
import com.sub.techsub.adapter.controller.api.EstabelecimentoAPI;
import com.sub.techsub.adapter.controller.resources.requests.EstabelecimentoRequest;
import com.sub.techsub.adapter.controller.resources.responses.EstabelecimentoResource;
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
class EstabelecimentoAPITest {

    @Mock
    private EstabelecimentoController estabelecimentoController;

    @InjectMocks
    private EstabelecimentoAPI estabelecimentoAPI;

    @Test
    void cadastrar_DeveRetornarIdCriado_QuandoRequisicaoForValida() {
        EstabelecimentoRequest estabelecimentoRequest = new EstabelecimentoRequest();
        Long idEsperado = 1L;
        when(estabelecimentoController.cadastrarEstabelecimento(any(EstabelecimentoRequest.class))).thenReturn(idEsperado);

        ResponseEntity<Long> resposta = estabelecimentoAPI.cadastrar(estabelecimentoRequest);

        assertNotNull(resposta);
        assertEquals(CREATED, resposta.getStatusCode());
        assertEquals(idEsperado, resposta.getBody());
        verify(estabelecimentoController, times(1)).cadastrarEstabelecimento(any(EstabelecimentoRequest.class));
    }

    @Test
    void listarTodos_DeveRetornarListaDeEstabelecimentos() {
        List<EstabelecimentoResource> listaEsperada = List.of(new EstabelecimentoResource(), new EstabelecimentoResource());
        when(estabelecimentoController.listarTodos()).thenReturn(listaEsperada);

        List<EstabelecimentoResource> resultado = estabelecimentoAPI.listarTodos();

        assertNotNull(resultado);
        assertEquals(listaEsperada.size(), resultado.size());
        verify(estabelecimentoController, times(1)).listarTodos();
    }

    @Test
    void filtrarEstabelecimento_DeveRetornarListaDeEstabelecimentosFiltrados() {
        List<EstabelecimentoResource> listaEsperada = List.of(new EstabelecimentoResource(), new EstabelecimentoResource());
        when(estabelecimentoController.filtroEstabelecimento(any(), any(), any(), any(), any(), any())).thenReturn(listaEsperada);

        List<EstabelecimentoResource> resultado = estabelecimentoAPI.filtrarEstabelecimento("nome", "localizacao", "servico", 4.0, 50.0, 200.0);

        assertNotNull(resultado);
        assertEquals(listaEsperada.size(), resultado.size());
        verify(estabelecimentoController, times(1)).filtroEstabelecimento(any(), any(), any(), any(), any(), any());
    }
}
