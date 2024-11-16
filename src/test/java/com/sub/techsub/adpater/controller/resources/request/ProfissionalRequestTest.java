package com.sub.techsub.adpater.controller.resources.request;

import com.sub.techsub.adapter.controller.resources.requests.ProfissionalRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
public class ProfissionalRequestTest {

    @Test
    void testConstructorAndGetters() {
        String nome = "Maria Roberta";
        Long servico = 1L;
        String horariosDisponiveis = "Segunda Sexta 9:00-19:00";
        Double tarifas = 100.00;

        ProfissionalRequest profissionalRequest = new ProfissionalRequest(nome, servico, horariosDisponiveis, tarifas);

        assertNotNull(profissionalRequest);
        assertEquals(nome, profissionalRequest.getNome());
        assertEquals(servico, profissionalRequest.getServico());
        assertEquals(horariosDisponiveis, profissionalRequest.getHorariosDisponiveis());
        assertEquals(tarifas, profissionalRequest.getTarifas());
    }

    @Test
    void testSetters() {
        ProfissionalRequest profissionalRequest = new ProfissionalRequest();

        String nome = "Maria Roberta";
        Long servico = 1L;
        String horariosDisponiveis = "Segunda Sexta 9:00-19:00";
        Double tarifas = 100.00;

        profissionalRequest.setNome(nome);
        profissionalRequest.setServico(servico);
        profissionalRequest.setHorariosDisponiveis(horariosDisponiveis);
        profissionalRequest.setTarifas(tarifas);

        assertEquals(nome, profissionalRequest.getNome());
        assertEquals(servico, profissionalRequest.getServico());
        assertEquals(horariosDisponiveis, profissionalRequest.getHorariosDisponiveis());
        assertEquals(tarifas, profissionalRequest.getTarifas());
    }

    @Test
    void testBuilder() {
        String nome = "Maria Roberta";
        Long servico = 1L;
        String horariosDisponiveis = "Segunda Sexta 9:00-19:00";
        Double tarifas = 100.00;

        ProfissionalRequest profissionalRequest = ProfissionalRequest.builder()
                .nome(nome)
                .servico(servico)
                .horariosDisponiveis(horariosDisponiveis)
                .tarifas(tarifas)
                .build();

        assertNotNull(profissionalRequest);
        assertEquals(nome, profissionalRequest.getNome());
        assertEquals(servico, profissionalRequest.getServico());
        assertEquals(horariosDisponiveis, profissionalRequest.getHorariosDisponiveis());
        assertEquals(tarifas, profissionalRequest.getTarifas());
    }
}
