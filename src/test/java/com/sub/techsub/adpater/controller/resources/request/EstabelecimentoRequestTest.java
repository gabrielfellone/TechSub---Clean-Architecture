package com.sub.techsub.adpater.controller.resources.request;

import com.sub.techsub.adapter.controller.resources.requests.EstabelecimentoRequest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
public class EstabelecimentoRequestTest {
    @Test
    void testConstructorAndGetters() {
        String nome = "Beleza";
        String endereco = "Rua Antonio Martins";
        List<Long> profissionais = List.of(1L, 2L);
        List<Long> servicos = List.of(1L, 2L);
        String horarioFuncionamento = "Segunda Sexta 9:00-19:00";
        List<String> fotos = List.of("foto1.jpg", "foto2.jpg");

        EstabelecimentoRequest estabelecimentoRequest = new EstabelecimentoRequest(nome, endereco, profissionais, servicos, horarioFuncionamento, fotos);

        assertNotNull(estabelecimentoRequest);
        assertEquals(nome, estabelecimentoRequest.getNome());
        assertEquals(endereco, estabelecimentoRequest.getEndereco());
        assertEquals(profissionais, estabelecimentoRequest.getProfissionais());
        assertEquals(servicos, estabelecimentoRequest.getServicos());
        assertEquals(horarioFuncionamento, estabelecimentoRequest.getHorarioFuncionamento());
        assertEquals(fotos, estabelecimentoRequest.getFotos());
    }

    @Test
    void testSetters() {
        EstabelecimentoRequest estabelecimentoRequest = new EstabelecimentoRequest();

        String nome = "Beleza";
        String endereco = "Rua Antonio Martins";
        List<Long> profissionais = List.of(1L, 2L);
        List<Long> servicos = List.of(1L, 2L);
        String horarioFuncionamento = "Segunda Sexta 9:00-19:00";
        List<String> fotos = List.of("foto1.jpg", "foto2.jpg");

        estabelecimentoRequest.setNome(nome);
        estabelecimentoRequest.setEndereco(endereco);
        estabelecimentoRequest.setProfissionais(profissionais);
        estabelecimentoRequest.setServicos(servicos);
        estabelecimentoRequest.setHorarioFuncionamento(horarioFuncionamento);
        estabelecimentoRequest.setFotos(fotos);

        assertEquals(nome, estabelecimentoRequest.getNome());
        assertEquals(endereco, estabelecimentoRequest.getEndereco());
        assertEquals(profissionais, estabelecimentoRequest.getProfissionais());
        assertEquals(servicos, estabelecimentoRequest.getServicos());
        assertEquals(horarioFuncionamento, estabelecimentoRequest.getHorarioFuncionamento());
        assertEquals(fotos, estabelecimentoRequest.getFotos());
    }

    @Test
    void testBuilder() {
        String nome = "Beleza";
        String endereco = "Rua Antonio Martins";
        List<Long> profissionais = List.of(1L, 2L);
        List<Long> servicos = List.of(1L, 2L);
        String horarioFuncionamento = "Segunda Sexta 9:00-19:00";
        List<String> fotos = List.of("foto1.jpg", "foto2.jpg");

        EstabelecimentoRequest estabelecimentoRequest = EstabelecimentoRequest.builder()
                .nome(nome)
                .endereco(endereco)
                .profissionais(profissionais)
                .servicos(servicos)
                .horarioFuncionamento(horarioFuncionamento)
                .fotos(fotos)
                .build();

        assertNotNull(estabelecimentoRequest);
        assertEquals(nome, estabelecimentoRequest.getNome());
        assertEquals(endereco, estabelecimentoRequest.getEndereco());
        assertEquals(profissionais, estabelecimentoRequest.getProfissionais());
        assertEquals(servicos, estabelecimentoRequest.getServicos());
        assertEquals(horarioFuncionamento, estabelecimentoRequest.getHorarioFuncionamento());
        assertEquals(fotos, estabelecimentoRequest.getFotos());
    }

}
