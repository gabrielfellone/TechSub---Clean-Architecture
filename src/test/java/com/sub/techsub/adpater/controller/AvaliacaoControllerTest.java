package com.sub.techsub.adpater.controller;


import com.sub.techsub.adapter.controller.AvaliacaoController;
import com.sub.techsub.adapter.controller.resources.requests.AvaliacaoRequest;
import com.sub.techsub.adapter.gateway.AvaliacaoGateway;
import com.sub.techsub.adapter.gateway.EstabelecimentoGateway;
import com.sub.techsub.adapter.gateway.ProfissionalGateway;
import com.sub.techsub.core.domain.model.Avaliacao;
import com.sub.techsub.core.domain.model.Estabelecimento;
import com.sub.techsub.core.domain.model.Profissional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringJUnitConfig
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AvaliacaoControllerTest {


    @Mock
    private EstabelecimentoGateway estabelecimentoGateway;

    @Mock
    private ProfissionalGateway profissionalGateway;

    @Mock
    private AvaliacaoGateway avaliacaoGateway;

    @InjectMocks
    private AvaliacaoController avaliacaoController;

    private AvaliacaoRequest avaliacaoRequest;
    private Estabelecimento estabelecimento;
    private Profissional profissional;

    @BeforeEach
    void setUp() {

        avaliacaoRequest = new AvaliacaoRequest();
        avaliacaoRequest.setEstabelecimentoId(1L);
        avaliacaoRequest.setProfissionalId(1L);
        avaliacaoRequest.setDescricao("Excelente serviço!");
        avaliacaoRequest.setTipo("SERVIÇO");
        avaliacaoRequest.setNota(5);

        estabelecimento = new Estabelecimento();
        estabelecimento.setId(1L);

        profissional = new Profissional();
        profissional.setId(1L);
    }

    @Test
    void realizarAvalicao_DeveRetornarIdAvaliacao() {
        when(estabelecimentoGateway.buscarEstabelecimentoPorId(1L)).thenReturn(Optional.of(estabelecimento));
        when(profissionalGateway.buscarProfissionalPorId(1L)).thenReturn(Optional.of(profissional));
        when(avaliacaoGateway.salvarAvaliacao(any())).thenReturn(new Avaliacao());

        avaliacaoController.realizarAvalicao(avaliacaoRequest);

        verify(avaliacaoGateway, times(1)).salvarAvaliacao(any());
        verify(profissionalGateway, times(1)).salvarProfissional(profissional);
        verify(estabelecimentoGateway, times(1)).salvarEstabelecimento(estabelecimento);
    }

    @Test
    void realizarAvalicao_EstabelecimentoNaoEncontrado_DeveLancarExcecao() {
        when(estabelecimentoGateway.buscarEstabelecimentoPorId(1L)).thenReturn(Optional.empty());
        when(profissionalGateway.buscarProfissionalPorId(1L)).thenReturn(Optional.of(profissional));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            avaliacaoController.realizarAvalicao(avaliacaoRequest);
        });
        assertEquals("Profissional ou Estabelecimento nao encontrados! Por favor insira outro ID.", exception.getMessage());
    }

    @Test
    void realizarAvalicao_ProfissionalNaoEncontrado_DeveLancarExcecao() {
        when(estabelecimentoGateway.buscarEstabelecimentoPorId(1L)).thenReturn(Optional.of(estabelecimento));
        when(profissionalGateway.buscarProfissionalPorId(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            avaliacaoController.realizarAvalicao(avaliacaoRequest);
        });
        assertEquals("Profissional ou Estabelecimento nao encontrados! Por favor insira outro ID.", exception.getMessage());
    }

    @Test
    void realizarAvalicao_EstabelecimentoEProfissionalNaoEncontrados_DeveLancarExcecao() {
        when(estabelecimentoGateway.buscarEstabelecimentoPorId(1L)).thenReturn(Optional.empty());
        when(profissionalGateway.buscarProfissionalPorId(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            avaliacaoController.realizarAvalicao(avaliacaoRequest);
        });
        assertEquals("Profissional ou Estabelecimento nao encontrados! Por favor insira outro ID.", exception.getMessage());
    }
}
