package com.sub.techsub.core.domain.model;

import com.sub.techsub.adapter.controller.resources.requests.EstabelecimentoRequest;
import com.sub.techsub.core.domain.presenter.FormatarValorFotosEstacionamento;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class EstabelecimentoModelTest {
    @Test
    void testCriacaoEstabelecimento() {

        String nome = "Estabelecimento X";
        String endereco = "Rua ABC, 123";
        String horarioFuncionamento = "Segunda a Sexta 9:00-18:00";

        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setNome(nome);
        estabelecimento.setEndereco(endereco);
        estabelecimento.setHorarioFuncionamento(horarioFuncionamento);

        assertNotNull(estabelecimento);
        assertEquals(nome, estabelecimento.getNome());
        assertEquals(endereco, estabelecimento.getEndereco());
        assertEquals(horarioFuncionamento, estabelecimento.getHorarioFuncionamento());
    }
    @Test
    void testCriacaoEstabelecimentoComRequest() {
        EstabelecimentoRequest request = new EstabelecimentoRequest();
        request.setNome("Estabelecimento ABC");
        request.setEndereco("Rua XYZ, 456");
        request.setHorarioFuncionamento("Segunda a Sexta 8:00-20:00");
        request.setFotos(List.of("foto1.jpg", "foto2.jpg"));

        Estabelecimento estabelecimento = new Estabelecimento(request);

        assertNotNull(estabelecimento);
        assertEquals("Estabelecimento ABC", estabelecimento.getNome());
        assertEquals("Rua XYZ, 456", estabelecimento.getEndereco());
        assertEquals("Segunda a Sexta 8:00-20:00", estabelecimento.getHorarioFuncionamento());
        assertEquals(2, estabelecimento.getFotos().length);
        assertEquals("foto1.jpg", estabelecimento.getFotos()[0]);
        assertEquals("foto2.jpg", estabelecimento.getFotos()[1]);
    }

    @Test
    void testConvertToDatabaseColumn() {
        String[] fotos = {"foto1.jpg", "foto2.jpg"};
        FormatarValorFotosEstacionamento conversor = new FormatarValorFotosEstacionamento();
        String json = conversor.convertToDatabaseColumn(fotos);
        assertNotNull(json);
        assertTrue(json.contains("foto1.jpg"));
        assertTrue(json.contains("foto2.jpg"));
    }

    @Test
    void testConvertToEntityAttribute() {
        String json = "[\"foto1.jpg\", \"foto2.jpg\"]";
        FormatarValorFotosEstacionamento conversor = new FormatarValorFotosEstacionamento();
        String[] fotos = conversor.convertToEntityAttribute(json);
        assertNotNull(fotos);
        assertEquals(2, fotos.length);
        assertEquals("foto1.jpg", fotos[0]);
        assertEquals("foto2.jpg", fotos[1]);
    }

    @Test
    void testRelacaoComAvaliacao() {

        Avaliacao avaliacao = new Avaliacao(1L, "Boa", 4, "Serviço");
        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setAvaliacao(avaliacao);


        assertNotNull(estabelecimento.getAvaliacao());
        assertEquals(4, estabelecimento.getAvaliacao().getNota());
        assertEquals("Boa", estabelecimento.getAvaliacao().getDescricao());
    }

    @Test
    void testRelacaoComProfissionais() {
        Profissional profissional1 = new Profissional();
        profissional1.setNome("Profissional 1");

        EstabelecimentoProfissional estabelecimentoProfissional = new EstabelecimentoProfissional();
        estabelecimentoProfissional.setProfissional(profissional1);
        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setProfissionais(List.of(estabelecimentoProfissional));

        assertNotNull(estabelecimento.getProfissionais());
        assertEquals(1, estabelecimento.getProfissionais().size());
        assertEquals("Profissional 1", estabelecimento.getProfissionais().get(0).getProfissional().getNome());
    }

    @Test
    void testRelacaoComServicos() {

        EstabelecimentoServico servico1 = new EstabelecimentoServico();
        servico1.setServicoId(1L);
        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setEstabelecimentoServicos(List.of(servico1));

        assertNotNull(estabelecimento.getEstabelecimentoServicos());
        assertEquals(1, estabelecimento.getEstabelecimentoServicos().size());

    }
}
