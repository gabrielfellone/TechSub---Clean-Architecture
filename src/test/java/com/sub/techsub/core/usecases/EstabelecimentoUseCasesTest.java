package com.sub.techsub.core.usecases;

import com.sub.techsub.adapter.controller.resources.responses.EstabelecimentoResource;

import com.sub.techsub.core.domain.model.Avaliacao;
import com.sub.techsub.core.domain.model.Profissional;
import com.sub.techsub.core.domain.model.Servico;
import com.sub.techsub.core.usecase.EstabelecimentoUseCases;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class EstabelecimentoUseCasesTest {

    private EstabelecimentoUseCases estabelecimentoUseCases;

    @BeforeEach
    void setUp() {
        estabelecimentoUseCases = new EstabelecimentoUseCases();
    }
    @Test
    void testFiltrarEstabelecimentoPorNome() {
        EstabelecimentoResource estabelecimento1 = new EstabelecimentoResource();

        estabelecimento1.setNome("Estabelecimento 1");
        estabelecimento1.setEndereco("Rua Das Verguilhas");
        estabelecimento1.setAvaliacao(new Avaliacao());
        estabelecimento1.setServicos(new ArrayList<>());
        estabelecimento1.setFotos(new ArrayList<>());
        estabelecimento1.setProfissionals(new ArrayList<>());
        estabelecimento1.setHorarioFuncionamento("Segunda a Sexta");

        EstabelecimentoResource estabelecimento2 = new EstabelecimentoResource();

        estabelecimento2.setNome("Estabelecimento 2");
        estabelecimento2.setEndereco("Rua Macarena");
        estabelecimento2.setAvaliacao(new Avaliacao());
        estabelecimento2.setServicos(new ArrayList<>());
        estabelecimento2.setFotos(new ArrayList<>());
        estabelecimento2.setProfissionals(new ArrayList<>());
        estabelecimento2.setHorarioFuncionamento("Terça a Sabado");

        List<EstabelecimentoResource> estabelecimentos = new ArrayList<>();

        estabelecimentos.add(estabelecimento1);
        estabelecimentos.add(estabelecimento2);

        List<EstabelecimentoResource> resultado = estabelecimentoUseCases.filtrarEstabelecimento(estabelecimentos, "Estabelecimento 1", null, null, null, null, null);

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getNome()).isEqualTo("Estabelecimento 1");
    }

    @Test
    void testFiltrarEstabelecimentoPorLocalizacao() {
        EstabelecimentoResource estabelecimento1 = new EstabelecimentoResource();

        estabelecimento1.setNome("Estabelecimento 1");
        estabelecimento1.setEndereco("Rua Das Verguilhas");
        estabelecimento1.setAvaliacao(new Avaliacao());
        estabelecimento1.setServicos(new ArrayList<>());
        estabelecimento1.setFotos(new ArrayList<>());
        estabelecimento1.setProfissionals(new ArrayList<>());
        estabelecimento1.setHorarioFuncionamento("Segunda a Sexta");

        EstabelecimentoResource estabelecimento2 = new EstabelecimentoResource();

        estabelecimento2.setNome("Estabelecimento 2");
        estabelecimento2.setEndereco("Rua Macarena");
        estabelecimento2.setAvaliacao(new Avaliacao());
        estabelecimento2.setServicos(new ArrayList<>());
        estabelecimento2.setFotos(new ArrayList<>());
        estabelecimento2.setProfissionals(new ArrayList<>());
        estabelecimento2.setHorarioFuncionamento("Terça a Sabado");

        List<EstabelecimentoResource> estabelecimentos = new ArrayList<>();

        estabelecimentos.add(estabelecimento1);
        estabelecimentos.add(estabelecimento2);

        List<EstabelecimentoResource> resultado = estabelecimentoUseCases.filtrarEstabelecimento(estabelecimentos, null, "Rua Macarena", null, null, null, null);

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getEndereco()).isEqualTo("Rua Macarena");
    }


    @Test
    void testFiltrarEstabelecimentoPorServicoOferecido() {
        EstabelecimentoResource estabelecimento1 = new EstabelecimentoResource();

        estabelecimento1.setNome("Estabelecimento 1");
        estabelecimento1.setEndereco("Rua Das Verguilhas");
        estabelecimento1.setAvaliacao(new Avaliacao());
        estabelecimento1.setServicos(new ArrayList<>());
        estabelecimento1.setFotos(new ArrayList<>());
        estabelecimento1.setProfissionals(new ArrayList<>());
        estabelecimento1.setHorarioFuncionamento("Segunda a Sexta");


        Servico servico = new Servico();
        servico.setNome("Serviço 1");

        List<Servico> servicos = new ArrayList<>();

        servicos.add(servico);

        estabelecimento1.setServicos(servicos);

        List<EstabelecimentoResource> estabelecimentos = new ArrayList<>();

        estabelecimentos.add(estabelecimento1);


        List<EstabelecimentoResource> resultado = estabelecimentoUseCases.filtrarEstabelecimento(estabelecimentos, null, null, "Serviço 1", null, null, null);

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getServicos().get(0).getNome()).isEqualTo("Serviço 1");
    }

    @Test
    void testFiltrarEstabelecimentoPorAvaliacaoMin() {
        EstabelecimentoResource estabelecimento1 = new EstabelecimentoResource();

        estabelecimento1.setNome("Estabelecimento 1");
        estabelecimento1.setEndereco("Rua Das Verguilhas");
        estabelecimento1.setAvaliacao(new Avaliacao());
        estabelecimento1.setServicos(new ArrayList<>());
        estabelecimento1.setFotos(new ArrayList<>());
        estabelecimento1.setProfissionals(new ArrayList<>());
        estabelecimento1.setHorarioFuncionamento("Segunda a Sexta");

        EstabelecimentoResource estabelecimento2 = new EstabelecimentoResource();

        estabelecimento2.setNome("Estabelecimento 2");
        estabelecimento2.setEndereco("Rua Macarena");
        estabelecimento2.setAvaliacao(new Avaliacao());
        estabelecimento2.setServicos(new ArrayList<>());
        estabelecimento2.setFotos(new ArrayList<>());
        estabelecimento2.setProfissionals(new ArrayList<>());
        estabelecimento2.setHorarioFuncionamento("Terça a Sabado");

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(5);

        estabelecimento1.setAvaliacao(avaliacao);

        Avaliacao avaliacao2 = new Avaliacao();
        avaliacao2.setNota(2);

        estabelecimento1.setAvaliacao(avaliacao);
        estabelecimento2.setAvaliacao(avaliacao2);

        List<EstabelecimentoResource> estabelecimentos = new ArrayList<>();

        estabelecimentos.add(estabelecimento1);
        estabelecimentos.add(estabelecimento2);

        List<EstabelecimentoResource> resultado = estabelecimentoUseCases.filtrarEstabelecimento(estabelecimentos, null, null, null, 3.0, null, null);

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getAvaliacao().getNota()).isGreaterThanOrEqualTo(3);
    }

    @Test
    void testFiltrarEstabelecimentoPorFaixaPreco() {
        EstabelecimentoResource estabelecimento1 = new EstabelecimentoResource();

        estabelecimento1.setNome("Estabelecimento 1");
        estabelecimento1.setEndereco("Rua Das Verguilhas");
        estabelecimento1.setAvaliacao(new Avaliacao());
        estabelecimento1.setServicos(new ArrayList<>());
        estabelecimento1.setFotos(new ArrayList<>());
        estabelecimento1.setProfissionals(new ArrayList<>());
        estabelecimento1.setHorarioFuncionamento("Segunda a Sexta");

        EstabelecimentoResource estabelecimento2 = new EstabelecimentoResource();

        estabelecimento2.setNome("Estabelecimento 2");
        estabelecimento2.setEndereco("Rua Macarena");
        estabelecimento2.setAvaliacao(new Avaliacao());
        estabelecimento2.setServicos(new ArrayList<>());
        estabelecimento2.setFotos(new ArrayList<>());
        estabelecimento2.setProfissionals(new ArrayList<>());
        estabelecimento2.setHorarioFuncionamento("Terça a Sabado");

        Profissional profissional = new Profissional();

        profissional.setTarifas(50.00);

        Profissional profissional2 = new Profissional();

        profissional2.setTarifas(150.00);

        List<Profissional> profissionals = List.of(profissional, profissional2);

        estabelecimento1.setProfissionals(profissionals);

        List<EstabelecimentoResource> estabelecimentos = new ArrayList<>();
        estabelecimentos.add(estabelecimento1);
        estabelecimentos.add(estabelecimento2);


        List<EstabelecimentoResource> resultado = estabelecimentoUseCases.filtrarEstabelecimento(estabelecimentos, null, null, null, null, 40.0, 100.0);

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getProfissionals().get(0).getTarifas()).isBetween(40.0, 100.0);
    }

    @Test
    void testFiltrarEstabelecimentoPorVariosParametros() {
        EstabelecimentoResource estabelecimento1 = new EstabelecimentoResource();

        estabelecimento1.setNome("Estabelecimento 1");
        estabelecimento1.setEndereco("Rua Das Verguilhas");
        estabelecimento1.setAvaliacao(new Avaliacao());
        estabelecimento1.setServicos(new ArrayList<>());
        estabelecimento1.setFotos(new ArrayList<>());
        estabelecimento1.setProfissionals(new ArrayList<>());
        estabelecimento1.setHorarioFuncionamento("Segunda a Sexta");

        EstabelecimentoResource estabelecimento2 = new EstabelecimentoResource();

        estabelecimento2.setNome("Estabelecimento 2");
        estabelecimento2.setEndereco("Rua Macarena");
        estabelecimento2.setAvaliacao(new Avaliacao());
        estabelecimento2.setServicos(new ArrayList<>());
        estabelecimento2.setFotos(new ArrayList<>());
        estabelecimento2.setProfissionals(new ArrayList<>());
        estabelecimento2.setHorarioFuncionamento("Terça a Sabado");

        Profissional profissional = new Profissional();

        profissional.setTarifas(50.00);

        Profissional profissional2 = new Profissional();

        profissional2.setTarifas(150.00);

        List<Profissional> profissionals = List.of(profissional, profissional2);

        estabelecimento1.setProfissionals(profissionals);

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(5);

        estabelecimento1.setAvaliacao(avaliacao);

        Avaliacao avaliacao2 = new Avaliacao();
        avaliacao2.setNota(2);

        estabelecimento1.setAvaliacao(avaliacao);
        estabelecimento2.setAvaliacao(avaliacao2);

        Servico servico = new Servico();
        servico.setNome("Serviço 1");

        Servico servico2 = new Servico();
        servico2.setNome("Serviço 2");

        List<Servico> servicos = new ArrayList<>();

        servicos.add(servico);
        servicos.add(servico2);

        estabelecimento1.setServicos(servicos);
        estabelecimento2.setServicos(servicos);

        List<EstabelecimentoResource> estabelecimentos = new ArrayList<>();

        estabelecimentos.add(estabelecimento1);
        estabelecimentos.add(estabelecimento2);

        List<EstabelecimentoResource> resultado = estabelecimentoUseCases.filtrarEstabelecimento(estabelecimentos, "Estabelecimento 1", "Rua Das Verguilhas", "Serviço 1", 4.0, 30.0, 100.0);

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getNome()).isEqualTo("Estabelecimento 1");
        assertThat(resultado.get(0).getEndereco()).isEqualTo("Rua Das Verguilhas");
        assertThat(resultado.get(0).getServicos().get(0).getNome()).isEqualTo("Serviço 1");
        assertThat(resultado.get(0).getAvaliacao().getNota()).isGreaterThanOrEqualTo(4);
        assertThat(resultado.get(0).getProfissionals().get(0).getTarifas()).isBetween(30.0, 100.0);
    }
}
