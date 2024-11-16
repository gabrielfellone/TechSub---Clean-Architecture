package com.sub.techsub.adpater.gateway;


import com.sub.techsub.adapter.gateway.AvaliacaoGateway;
import com.sub.techsub.core.domain.model.Avaliacao;
import com.sub.techsub.infrastructure.repository.IAvaliacaoRepository;
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
public class AvaliacaoGatewayTest {


    @Mock
    private IAvaliacaoRepository avaliacaoRepository;

    @InjectMocks
    private AvaliacaoGateway avaliacaoGateway;

    @Test
    void testSalvarAvaliacao() {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setId(1L);
        avaliacao.setDescricao("Muito bom");
        avaliacao.setNota(5);
        avaliacao.setTipo("Positiva");
        when(avaliacaoRepository.save(avaliacao)).thenReturn(avaliacao);

        Avaliacao savedAvaliacao = avaliacaoGateway.salvarAvaliacao(avaliacao);
        assertNotNull(savedAvaliacao);
        assertEquals(1L, savedAvaliacao.getId());
        assertEquals("Muito bom", savedAvaliacao.getDescricao());
        assertEquals(5, savedAvaliacao.getNota());
        assertEquals("Positiva", savedAvaliacao.getTipo());

        verify(avaliacaoRepository, times(1)).save(avaliacao);
    }

    @Test
    void testBuscarAvaliacaoPorId() {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setId(1L);
        avaliacao.setDescricao("Muito bom");
        avaliacao.setNota(5);
        avaliacao.setTipo("Positiva");

        when(avaliacaoRepository.findById(1L)).thenReturn(Optional.of(avaliacao));

        Optional<Avaliacao> foundAvaliacao = avaliacaoGateway.buscarAvaliacaoPorId(1L);

        assertTrue(foundAvaliacao.isPresent());
        assertEquals(1L, foundAvaliacao.get().getId());
        assertEquals("Muito bom", foundAvaliacao.get().getDescricao());
        assertEquals(5, foundAvaliacao.get().getNota());
        assertEquals("Positiva", foundAvaliacao.get().getTipo());

        verify(avaliacaoRepository, times(1)).findById(1L);
    }

    @Test
    void testBuscarAvaliacaoPorIdNaoEncontrado() {
        when(avaliacaoRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Avaliacao> foundAvaliacao = avaliacaoGateway.buscarAvaliacaoPorId(1L);
        assertFalse(foundAvaliacao.isPresent());
        verify(avaliacaoRepository, times(1)).findById(1L);
    }

    @Test
    void testFlush() {
        avaliacaoGateway.flush();
        verify(avaliacaoRepository, times(1)).flush();
    }
}
