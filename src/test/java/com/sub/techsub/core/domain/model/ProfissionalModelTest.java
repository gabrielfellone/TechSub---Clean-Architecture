package com.sub.techsub.core.domain.model;

import com.sub.techsub.adapter.controller.resources.requests.ProfissionalRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProfissionalModelTest {

    @Test
    void testConstrutorPadrao() {
        Profissional profissional = new Profissional();
        assertNotNull(profissional);
        assertNull(profissional.getId());
        assertNull(profissional.getNome());
        assertNull(profissional.getHorariosDisponiveis());
        assertNull(profissional.getTarifas());
    }

    @Test
    void testConstrutorComProfissionalRequest() {

        ProfissionalRequest request = new ProfissionalRequest();
        request.setTarifas(150.0);
        request.setNome("João");
        request.setHorariosDisponiveis("Segunda a Sexta, 9h-18h");
        Profissional profissional = new Profissional(request);

        assertEquals("João", profissional.getNome());
        assertEquals("Segunda a Sexta, 9h-18h", profissional.getHorariosDisponiveis());
        assertEquals(150.0, profissional.getTarifas());
    }

    @Test
    void testGettersAndSetters() {

        Profissional profissional = new Profissional();
        profissional.setNome("Maria");
        profissional.setHorariosDisponiveis("Terça a Quinta, 10h-16h");
        profissional.setTarifas(120.0);

        assertEquals("Maria", profissional.getNome());
        assertEquals("Terça a Quinta, 10h-16h", profissional.getHorariosDisponiveis());
        assertEquals(120.0, profissional.getTarifas());
    }

    @Test
    void testRelacionamentoComAvaliacao() {
        Avaliacao avaliacao = new Avaliacao(1L, "Muito bom", 5, "Positiva");
        Profissional profissional = new Profissional();
        profissional.setAvaliacao(avaliacao);
        assertNotNull(profissional.getAvaliacao());
        assertEquals(avaliacao, profissional.getAvaliacao());
    }

    @Test
    void testRelacionamentoComServico() {

        Servico servico = new Servico();
        servico.setNome("Manicure");
        servico.setId(1L);
        Profissional profissional = new Profissional();
        profissional.setServico(servico);
        assertNotNull(profissional.getServico());
        assertEquals(servico, profissional.getServico());
    }

}
