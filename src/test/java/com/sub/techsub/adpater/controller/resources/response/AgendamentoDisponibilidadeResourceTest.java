package com.sub.techsub.adpater.controller.resources.response;

import com.sub.techsub.adapter.controller.resources.responses.AgendamentoDisponibilidadeResource;
import com.sub.techsub.core.domain.model.Profissional;
import com.sub.techsub.core.domain.model.Servico;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AgendamentoDisponibilidadeResourceTest {

    @Test
    void testConstructorAndGetters() {
        List<Profissional> profissionais = List.of(new Profissional(), new Profissional());
        List<Servico> servicos = List.of(new Servico(), new Servico());

        AgendamentoDisponibilidadeResource resource = new AgendamentoDisponibilidadeResource(profissionais, servicos);

        assertNotNull(resource);
        assertEquals(profissionais, resource.getProfissionais());
        assertEquals(servicos, resource.getServicos());
    }

    @Test
    void testSetters() {
        AgendamentoDisponibilidadeResource resource = new AgendamentoDisponibilidadeResource();

        List<Profissional> profissionais = List.of(new Profissional(), new Profissional());
        List<Servico> servicos = List.of(new Servico(), new Servico());

        resource.setProfissionais(profissionais);
        resource.setServicos(servicos);

        assertEquals(profissionais, resource.getProfissionais());
        assertEquals(servicos, resource.getServicos());
    }

    @Test
    void testBuilder() {
        List<Profissional> profissionais = List.of(new Profissional(), new Profissional());
        List<Servico> servicos = List.of(new Servico(), new Servico());

        AgendamentoDisponibilidadeResource resource = AgendamentoDisponibilidadeResource.builder()
                .profissionais(profissionais)
                .servicos(servicos)
                .build();

        assertNotNull(resource);
        assertEquals(profissionais, resource.getProfissionais());
        assertEquals(servicos, resource.getServicos());
    }
}


