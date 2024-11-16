package com.sub.techsub.core.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EstabelecimentoServicoIdTest {

    @Test
    void testEqualsEHashCode() {

        Long estabelecimentoId1 = 1L;
        Long servicoId1 = 2L;
        Long estabelecimentoId2 = 1L;
        Long servicoId2 = 2L;

        EstabelecimentoServicoId chaveComposta1 = new EstabelecimentoServicoId(estabelecimentoId1, servicoId1);
        EstabelecimentoServicoId chaveComposta2 = new EstabelecimentoServicoId(estabelecimentoId2, servicoId2);
        assertEquals(chaveComposta1, chaveComposta2);
        assertEquals(chaveComposta1.hashCode(), chaveComposta2.hashCode());
    }

    @Test
    void testEqualsComValoresDiferentes() {
        Long estabelecimentoId1 = 1L;
        Long servicoId1 = 2L;
        Long estabelecimentoId2 = 3L;
        Long servicoId2 = 4L;

        EstabelecimentoServicoId chaveComposta1 = new EstabelecimentoServicoId(estabelecimentoId1, servicoId1);
        EstabelecimentoServicoId chaveComposta2 = new EstabelecimentoServicoId(estabelecimentoId2, servicoId2);

        assertNotEquals(chaveComposta1, chaveComposta2);
    }

    @Test
    void testEqualsComNull() {
        Long estabelecimentoId1 = 1L;
        Long servicoId1 = 2L;
        EstabelecimentoServicoId chaveComposta1 = new EstabelecimentoServicoId(estabelecimentoId1, servicoId1);
        assertNotEquals(null, chaveComposta1);
    }
}
