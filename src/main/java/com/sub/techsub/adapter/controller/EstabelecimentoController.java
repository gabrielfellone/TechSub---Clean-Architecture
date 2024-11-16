package com.sub.techsub.adapter.controller;

import com.sub.techsub.adapter.controller.resources.requests.EstabelecimentoRequest;
import com.sub.techsub.adapter.controller.resources.responses.EstabelecimentoResource;
import com.sub.techsub.adapter.gateway.*;
import com.sub.techsub.core.domain.model.*;
import com.sub.techsub.core.usecase.EstabelecimentoUseCases;
import com.sub.techsub.core.usecase.exception.ProfissionalException;
import com.sub.techsub.core.usecase.exception.ServicosException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EstabelecimentoController {

    //Use cases ---

    @Autowired
    EstabelecimentoUseCases estabelecimentoUseCases;


    //Gateways para acessar o banco ---

    @Autowired
    EstabelecimentoGateway estabelecimentoGateway;

    @Autowired
    ProfissionalGateway profissionalGateway;

    @Autowired
    ServicoGateway servicoGateway;

    @Autowired
    EstabelecimentoServicoGateway estabelecimentoServicoGateway;

    @Autowired
    EstabelecimentoProfissionalGateway estabelecimentoProfissionalGateway;

    public Long cadastrarEstabelecimento(EstabelecimentoRequest request){

        Estabelecimento estabelecimento = new Estabelecimento(request);

        Long id = estabelecimentoGateway.salvarEstabelecimento(estabelecimento).getId();

        estabelecimento.setId(id);

        salvarServicos(request.getServicos(), estabelecimento);

        salvarProfissionais(request.getProfissionais(), estabelecimento);

        return id;

    }
    public List<EstabelecimentoResource> listarTodos() {
        log.info("Buscando todos os estabelecimentos na base...");
        List<Estabelecimento> estabelecimentos = estabelecimentoGateway.listarEstabelecimentos();

        return estabelecimentos.stream()
                .filter(estabelecimento -> !estabelecimento.getEstabelecimentoServicos().isEmpty())
                .map(this::convertToEstabelecimentoResource)
                .collect(Collectors.toList());
    }

    private EstabelecimentoResource convertToEstabelecimentoResource(Estabelecimento estabelecimento) {
        List<Servico> servicos = estabelecimento.getEstabelecimentoServicos().stream()
                .map(servico -> servicoGateway.buscarServicoPorReference(servico.getServicoId()))
                .collect(Collectors.toList());
        List<Profissional> profissionais = estabelecimento.getProfissionais().stream()
                .map(profissional -> profissionalGateway.buscarProfissionalPorReference(profissional.getProfissionalId()))
                .collect(Collectors.toList());

        return EstabelecimentoResource.builder()
                .nome(estabelecimento.getNome())
                .endereco(estabelecimento.getEndereco())
                .horarioFuncionamento(estabelecimento.getHorarioFuncionamento())
                .fotos(Arrays.stream(estabelecimento.getFotos()).collect(Collectors.toList()))
                .servicos(servicos)
                .profissionals(profissionais)
                .avaliacao(estabelecimento.getAvaliacao())
                .build();
    }

    public void salvarServicos(List<Long> servicosids, Estabelecimento estabelecimento){
        servicosids.forEach(id -> {
            try {
                EstabelecimentoServico estabelecimentoServico = new EstabelecimentoServico();
                Servico servico = servicoGateway.buscarServicoPorReference(id);

                estabelecimentoServico.setEstabelecimentoId(estabelecimento.getId());
                estabelecimentoServico.setServicoId(servico.getId());
                estabelecimentoServicoGateway.salvarRelacionamentoEstabelecimentoServico(estabelecimentoServico);
            } catch (RuntimeException e){
                throw new ServicosException("Servicos nao encontrados");
            }
        });
    }

    public void salvarProfissionais(List<Long> profissionaisIds, Estabelecimento estabelecimento){
        profissionaisIds.forEach(id -> {
            try {
                EstabelecimentoProfissional estabelecimentoProfissional = new EstabelecimentoProfissional();
                Profissional profissional = profissionalGateway.buscarProfissionalPorReference(id);

                estabelecimentoProfissional.setEstabelecimentoId(estabelecimento.getId());
                estabelecimentoProfissional.setProfissionalId(profissional.getId());
                estabelecimentoProfissionalGateway.salvarRelacionamentoEstabelecimentoProfissional(estabelecimentoProfissional);
            } catch (RuntimeException e){
                throw new ProfissionalException("Profissionais nao encontrados");
            }
        });
    }

    public List<EstabelecimentoResource> filtroEstabelecimento(String nome, String localizacao,
                                                               String servicoOferecido,
                                                               Double avaliacaoMin, Double faixaPrecoMin, Double faixaPrecoMax) {

        List<EstabelecimentoResource> estabelecimentos = this.listarTodos();
        return estabelecimentoUseCases
                .filtrarEstabelecimento(estabelecimentos, nome, localizacao, servicoOferecido, avaliacaoMin, faixaPrecoMin, faixaPrecoMax);
    }
}
