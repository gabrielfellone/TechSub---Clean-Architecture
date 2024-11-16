package com.sub.techsub.core.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EstabelecimentoProfissionalIdTest {

    @Test
    void testCriacaoChaveComposta() {
        Long estabelecimentoId = 1L;
        Long profissionalId = 2L;
        EstabelecimentoProfissionalId chaveComposta = new EstabelecimentoProfissionalId(estabelecimentoId, profissionalId);
        assertNotNull(chaveComposta);
        assertEquals(estabelecimentoId, chaveComposta.getEstabelecimentoId());
        assertEquals(profissionalId, chaveComposta.getProfissionalId());
    }

    @Test
    void testEqualsEHashCode() {
        Long estabelecimentoId1 = 1L;
        Long profissionalId1 = 2L;
        Long estabelecimentoId2 = 1L;
        Long profissionalId2 = 2L;
        EstabelecimentoProfissionalId chaveComposta1 = new EstabelecimentoProfissionalId(estabelecimentoId1, profissionalId1);
        EstabelecimentoProfissionalId chaveComposta2 = new EstabelecimentoProfissionalId(estabelecimentoId2, profissionalId2);
        assertEquals(chaveComposta1, chaveComposta2);
        assertEquals(chaveComposta1.hashCode(), chaveComposta2.hashCode());
    }

    @Test
    void testEqualsComValoresDiferentes() {
        Long estabelecimentoId1 = 1L;
        Long profissionalId1 = 2L;
        Long estabelecimentoId2 = 3L;
        Long profissionalId2 = 4L;
        EstabelecimentoProfissionalId chaveComposta1 = new EstabelecimentoProfissionalId(estabelecimentoId1, profissionalId1);
        EstabelecimentoProfissionalId chaveComposta2 = new EstabelecimentoProfissionalId(estabelecimentoId2, profissionalId2);
        assertNotEquals(chaveComposta1, chaveComposta2);
    }
}
