package com.sub.techsub.adpater.controller.resources.response;

import com.sub.techsub.adapter.controller.resources.responses.EstabelecimentoResource;
import com.sub.techsub.core.domain.model.Avaliacao;
import com.sub.techsub.core.domain.model.Profissional;
import com.sub.techsub.core.domain.model.Servico;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
public class EstabelecimentoResourceTest {

    @Test
    void testConstructorAndGetters() {
        String nome = "Nome Teste";
        String endereco = "Endereço Teste";
        List<Servico> servicos = List.of(new Servico(), new Servico());
        List<Profissional> profissionals = List.of(new Profissional(), new Profissional());
        String horarioFuncionamento = "08:00 - 18:00";
        List<String> fotos = List.of("foto1.jpg", "foto2.jpg");
        Avaliacao avaliacao = new Avaliacao();

        EstabelecimentoResource estabelecimentoResource = new EstabelecimentoResource(nome, endereco, servicos, profissionals, horarioFuncionamento, fotos, avaliacao);

        assertNotNull(estabelecimentoResource);
        assertEquals(nome, estabelecimentoResource.getNome());
        assertEquals(endereco, estabelecimentoResource.getEndereco());
        assertEquals(servicos, estabelecimentoResource.getServicos());
        assertEquals(profissionals, estabelecimentoResource.getProfissionals());
        assertEquals(horarioFuncionamento, estabelecimentoResource.getHorarioFuncionamento());
        assertEquals(fotos, estabelecimentoResource.getFotos());
        assertEquals(avaliacao, estabelecimentoResource.getAvaliacao());
    }

    @Test
    void testSetters() {
        EstabelecimentoResource estabelecimentoResource = new EstabelecimentoResource();

        String nome = "Nome Teste";
        String endereco = "Endereço Teste";
        List<Servico> servicos = List.of(new Servico(), new Servico());
        List<Profissional> profissionals = List.of(new Profissional(), new Profissional());
        String horarioFuncionamento = "08:00 - 18:00";
        List<String> fotos = List.of("foto1.jpg", "foto2.jpg");
        Avaliacao avaliacao = new Avaliacao();

        estabelecimentoResource.setNome(nome);
        estabelecimentoResource.setEndereco(endereco);
        estabelecimentoResource.setServicos(servicos);
        estabelecimentoResource.setProfissionals(profissionals);
        estabelecimentoResource.setHorarioFuncionamento(horarioFuncionamento);
        estabelecimentoResource.setFotos(fotos);
        estabelecimentoResource.setAvaliacao(avaliacao);

        assertEquals(nome, estabelecimentoResource.getNome());
        assertEquals(endereco, estabelecimentoResource.getEndereco());
        assertEquals(servicos, estabelecimentoResource.getServicos());
        assertEquals(profissionals, estabelecimentoResource.getProfissionals());
        assertEquals(horarioFuncionamento, estabelecimentoResource.getHorarioFuncionamento());
        assertEquals(fotos, estabelecimentoResource.getFotos());
        assertEquals(avaliacao, estabelecimentoResource.getAvaliacao());
    }

    @Test
    void testBuilder() {
        String nome = "Nome Teste";
        String endereco = "Endereço Teste";
        List<Servico> servicos = List.of(new Servico(), new Servico());
        List<Profissional> profissionals = List.of(new Profissional(), new Profissional());
        String horarioFuncionamento = "08:00 - 18:00";
        List<String> fotos = List.of("foto1.jpg", "foto2.jpg");
        Avaliacao avaliacao = new Avaliacao();

        EstabelecimentoResource estabelecimentoResource = EstabelecimentoResource.builder()
                .nome(nome)
                .endereco(endereco)
                .servicos(servicos)
                .profissionals(profissionals)
                .horarioFuncionamento(horarioFuncionamento)
                .fotos(fotos)
                .avaliacao(avaliacao)
                .build();

        assertNotNull(estabelecimentoResource);
        assertEquals(nome, estabelecimentoResource.getNome());
        assertEquals(endereco, estabelecimentoResource.getEndereco());
        assertEquals(servicos, estabelecimentoResource.getServicos());
        assertEquals(profissionals, estabelecimentoResource.getProfissionals());
        assertEquals(horarioFuncionamento, estabelecimentoResource.getHorarioFuncionamento());
        assertEquals(fotos, estabelecimentoResource.getFotos());
        assertEquals(avaliacao, estabelecimentoResource.getAvaliacao());
    }
}

