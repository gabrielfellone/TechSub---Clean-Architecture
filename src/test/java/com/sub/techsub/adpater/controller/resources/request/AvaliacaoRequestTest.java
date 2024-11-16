package com.sub.techsub.adpater.controller.resources.request;

import com.sub.techsub.adapter.controller.resources.requests.AvaliacaoRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AvaliacaoRequestTest {

    @Test
    void testConstructorAndGetters() {
        String descricao = "Muito bom o serviço";
        Integer nota = 4;
        String tipo = "Massagem";
        Long profissionalId = 1L;
        Long estabelecimentoId = 1L;

        AvaliacaoRequest avaliacaoRequest = new AvaliacaoRequest(descricao, nota, tipo, profissionalId, estabelecimentoId);

        assertNotNull(avaliacaoRequest);
        assertEquals(descricao, avaliacaoRequest.getDescricao());
        assertEquals(nota, avaliacaoRequest.getNota());
        assertEquals(tipo, avaliacaoRequest.getTipo());
        assertEquals(profissionalId, avaliacaoRequest.getProfissionalId());
        assertEquals(estabelecimentoId, avaliacaoRequest.getEstabelecimentoId());
    }

    @Test
    void testSetters() {
        AvaliacaoRequest avaliacaoRequest = new AvaliacaoRequest();

        String descricao = "Muito bom o serviço";
        Integer nota = 4;
        String tipo = "Massagem";
        Long profissionalId = 1L;
        Long estabelecimentoId = 1L;

        avaliacaoRequest.setDescricao(descricao);
        avaliacaoRequest.setNota(nota);
        avaliacaoRequest.setTipo(tipo);
        avaliacaoRequest.setProfissionalId(profissionalId);
        avaliacaoRequest.setEstabelecimentoId(estabelecimentoId);

        assertEquals(descricao, avaliacaoRequest.getDescricao());
        assertEquals(nota, avaliacaoRequest.getNota());
        assertEquals(tipo, avaliacaoRequest.getTipo());
        assertEquals(profissionalId, avaliacaoRequest.getProfissionalId());
        assertEquals(estabelecimentoId, avaliacaoRequest.getEstabelecimentoId());
    }

    @Test
    void testBuilder() {
        String descricao = "Muito bom o serviço";
        Integer nota = 4;
        String tipo = "Massagem";
        Long profissionalId = 1L;
        Long estabelecimentoId = 1L;

        AvaliacaoRequest avaliacaoRequest = AvaliacaoRequest.builder()
                .descricao(descricao)
                .nota(nota)
                .tipo(tipo)
                .profissionalId(profissionalId)
                .estabelecimentoId(estabelecimentoId)
                .build();

        assertNotNull(avaliacaoRequest);
        assertEquals(descricao, avaliacaoRequest.getDescricao());
        assertEquals(nota, avaliacaoRequest.getNota());
        assertEquals(tipo, avaliacaoRequest.getTipo());
        assertEquals(profissionalId, avaliacaoRequest.getProfissionalId());
        assertEquals(estabelecimentoId, avaliacaoRequest.getEstabelecimentoId());
    }
}
