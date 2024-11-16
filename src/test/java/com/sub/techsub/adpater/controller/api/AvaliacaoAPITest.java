package com.sub.techsub.adpater.controller.api;

import com.sub.techsub.adapter.controller.AvaliacaoController;
import com.sub.techsub.adapter.controller.api.AvaliacaoAPI;
import com.sub.techsub.adapter.controller.resources.requests.AvaliacaoRequest;
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
class AvaliacaoAPITest {

    @Mock
    private AvaliacaoController avaliacaoController;

    @InjectMocks
    private AvaliacaoAPI avaliacaoAPI;

    @Test
    void realizarAvalicao_DeveRetornarIdCriado_QuandoRequisicaoForValida() {
        AvaliacaoRequest avaliacaoRequest = new AvaliacaoRequest();
        Long idEsperado = 1L;
        when(avaliacaoController.realizarAvalicao(any(AvaliacaoRequest.class))).thenReturn(idEsperado);

        ResponseEntity<Long> resposta = avaliacaoAPI.realizarAvalicao(avaliacaoRequest);

        assertNotNull(resposta);
        assertEquals(CREATED, resposta.getStatusCode());
        assertEquals(idEsperado, resposta.getBody());
        verify(avaliacaoController, times(1)).realizarAvalicao(any(AvaliacaoRequest.class));
    }
}
