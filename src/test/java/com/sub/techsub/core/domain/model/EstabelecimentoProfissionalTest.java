package com.sub.techsub.core.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EstabelecimentoProfissionalTest {

    @Test
    void testCriacaoEstabelecimentoProfissional() {
        Long estabelecimentoId = 1L;
        Long profissionalId = 2L;
        EstabelecimentoProfissional estabelecimentoProfissional = new EstabelecimentoProfissional(estabelecimentoId, profissionalId, null, null);
        assertNotNull(estabelecimentoProfissional);
        assertEquals(estabelecimentoId, estabelecimentoProfissional.getEstabelecimentoId());
        assertEquals(profissionalId, estabelecimentoProfissional.getProfissionalId());
    }

    @Test
    void testAssociacaoComEstabelecimentoEProfissional() {
        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setId(1L);
        estabelecimento.setNome("Estabelecimento FIAP");

        Profissional profissional = new Profissional();
        profissional.setId(2L);
        profissional.setNome("Profissional Maria Roberta");

        EstabelecimentoProfissional estabelecimentoProfissional = new EstabelecimentoProfissional(1L, 2L, estabelecimento, profissional);

        assertNotNull(estabelecimentoProfissional.getEstabelecimento());
        assertNotNull(estabelecimentoProfissional.getProfissional());
        assertEquals("Estabelecimento FIAP", estabelecimentoProfissional.getEstabelecimento().getNome());
        assertEquals("Profissional Maria Roberta", estabelecimentoProfissional.getProfissional().getNome());
    }

    @Test
    void testChavesCompostas() {
        Long estabelecimentoId = 1L;
        Long profissionalId = 2L;

        EstabelecimentoProfissional estabelecimentoProfissional = new EstabelecimentoProfissional(estabelecimentoId, profissionalId, null, null);

        assertEquals(estabelecimentoId, estabelecimentoProfissional.getEstabelecimentoId());
        assertEquals(profissionalId, estabelecimentoProfissional.getProfissionalId());
    }
}
