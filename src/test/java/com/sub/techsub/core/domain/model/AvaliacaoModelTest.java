package com.sub.techsub.core.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AvaliacaoModelTest {

    @Test
    void testCriacaoAvaliacao() {

        String descricao = "Excelente serviço!";
        Integer nota = 5;
        String tipo = "Positiva";

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setDescricao(descricao);
        avaliacao.setNota(nota);
        avaliacao.setTipo(tipo);
        assertNotNull(avaliacao);
        assertEquals(descricao, avaliacao.getDescricao());
        assertEquals(nota, avaliacao.getNota());
        assertEquals(tipo, avaliacao.getTipo());
    }

    @Test
    void testEqualsAndHashCode() {

        String descricao = "Excelente serviço!";
        Integer nota = 5;
        String tipo = "Positiva";

        Avaliacao avaliacao1 = new Avaliacao();
        avaliacao1.setDescricao(descricao);
        avaliacao1.setNota(nota);
        avaliacao1.setTipo(tipo);

        Avaliacao avaliacao2 = new Avaliacao();
        avaliacao2.setDescricao(descricao);
        avaliacao2.setNota(nota);
        avaliacao2.setTipo(tipo);

        assertEquals(avaliacao1, avaliacao2);
        assertEquals(avaliacao1.hashCode(), avaliacao2.hashCode());
    }

    @Test
    void testNotEquals() {

        Avaliacao avaliacao1 = new Avaliacao();
        avaliacao1.setDescricao("Excelente serviço!");
        avaliacao1.setNota(5);
        avaliacao1.setTipo("Positiva");

        Avaliacao avaliacao2 = new Avaliacao();
        avaliacao2.setDescricao("Bom serviço!");
        avaliacao2.setNota(4);
        avaliacao2.setTipo("Neutra");


        assertNotEquals(avaliacao1, avaliacao2);
    }


}
