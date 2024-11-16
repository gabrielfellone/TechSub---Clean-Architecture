package com.sub.techsub.adapter.controller;

import com.sub.techsub.adapter.controller.resources.requests.AvaliacaoRequest;
import com.sub.techsub.adapter.gateway.AvaliacaoGateway;
import com.sub.techsub.adapter.gateway.EstabelecimentoGateway;
import com.sub.techsub.adapter.gateway.ProfissionalGateway;
import com.sub.techsub.core.domain.model.Avaliacao;
import com.sub.techsub.core.domain.model.Estabelecimento;
import com.sub.techsub.core.domain.model.Profissional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AvaliacaoController {

    //Gateways para acessar o banco ---

    @Autowired
    EstabelecimentoGateway estabelecimentoGateway;

    @Autowired
    ProfissionalGateway profissionalGateway;

    @Autowired
    AvaliacaoGateway avaliacaoGateway;

    public Long realizarAvalicao(AvaliacaoRequest avaliacaoRequest) {
        log.info("Realizando avaliação...");

        Optional<Estabelecimento> estabelecimento =
                estabelecimentoGateway.buscarEstabelecimentoPorId(avaliacaoRequest.getEstabelecimentoId());

        Optional<Profissional> profissional =
                profissionalGateway.buscarProfissionalPorId(avaliacaoRequest.getProfissionalId());

        if(estabelecimento.isPresent()
                && profissional.isPresent()){

            Avaliacao avaliacao = Avaliacao.builder()
                    .descricao(avaliacaoRequest.getDescricao())
                    .tipo(avaliacaoRequest.getTipo())
                    .nota(avaliacaoRequest.getNota()).build();

            Long idAvaliacao = avaliacaoGateway.salvarAvaliacao(avaliacao).getId();

            profissional.get().setAvaliacao(avaliacao);
            estabelecimento.get().setAvaliacao(avaliacao);

            log.info("Registrando ultima avaliacao para Estabelecimento e Profissional");

            profissionalGateway.salvarProfissional(profissional.get());
            estabelecimentoGateway.salvarEstabelecimento(estabelecimento.get());

            return idAvaliacao;

        } else throw new RuntimeException("Profissional ou Estabelecimento nao encontrados! Por favor insira outro ID.");
    }
}
