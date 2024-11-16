package com.sub.techsub.core.domain.model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ServicoModelTest {

    @Test
    void testConstrutorPadrao() {

        Servico servico = new Servico();
        assertNotNull(servico);
        assertNull(servico.getId());
        assertNull(servico.getNome());
    }

    @Test
    void testConstrutorComParametros() {
        Servico servico = new Servico(1L, "Corte de Cabelo");
        assertEquals(1L, servico.getId());
        assertEquals("Corte de Cabelo", servico.getNome());
    }

    @Test
    void testGettersAndSetters() {
        Servico servico = new Servico();
        servico.setNome("Massagem");
        assertEquals("Massagem", servico.getNome());
    }
}


