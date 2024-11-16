package com.sub.techsub.core.usecase;

import com.sub.techsub.adapter.controller.resources.responses.EstabelecimentoResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EstabelecimentoUseCases {

    public List<EstabelecimentoResource> filtrarEstabelecimento(List<EstabelecimentoResource> estabelecimentos, String nome, String localizacao, String servicoOferecido,
                                                               Double avaliacaoMin, Double faixaPrecoMin, Double faixaPrecoMax) {


        List<EstabelecimentoResource> estabelecimentoFiltrado = new ArrayList<>();

        log.info("Realizando Filtro no Estabelecimento por base nos inputs...");


        if(nome != null){
            log.info("Procurando estabelecimento com o nome {}", nome);
            estabelecimentoFiltrado = estabelecimentos.stream().filter(e -> e.getNome().equalsIgnoreCase(nome)).collect(Collectors.toList());
        }

        if(localizacao != null){
            log.info("Procurando estabelecimento com a localizacao exata {}", localizacao);
            estabelecimentoFiltrado = estabelecimentos.stream().filter(e -> e.getEndereco().equalsIgnoreCase(localizacao)).collect(Collectors.toList());
        }

        if(servicoOferecido != null){
            log.info("Procurando servicos ofertados pelo estabelecimento {}", servicoOferecido);
            if(estabelecimentoFiltrado.isEmpty()) {
                estabelecimentoFiltrado.addAll(estabelecimentos);
            }

            List<EstabelecimentoResource> filtroTemp = new ArrayList<>();

            estabelecimentoFiltrado.forEach(e -> {
                e.getServicos().forEach(servico -> {
                    if(servico.getNome().equalsIgnoreCase(servicoOferecido)){
                        filtroTemp.add(e);
                    }
                });
            });

            estabelecimentoFiltrado = filtroTemp;
        }

        if(avaliacaoMin != null){
            log.info("Procurando estabelecimentos com avaliacao minima de {}", avaliacaoMin);
            estabelecimentoFiltrado = estabelecimentos.stream().filter(e -> e.getAvaliacao().getNota() >= avaliacaoMin).collect(Collectors.toList());
        }

        if(faixaPrecoMin != null && faixaPrecoMax != null){
            log.info("Procurando estabelecimentos com profissioanais nesta faixa de preco min {} e max {}", faixaPrecoMin,faixaPrecoMax );
            if(estabelecimentoFiltrado.isEmpty()) {
                estabelecimentoFiltrado.addAll(estabelecimentos);
            }

            List<EstabelecimentoResource> filtroTemp = new ArrayList<>();

            estabelecimentoFiltrado.forEach(e -> {
                e.getProfissionals().forEach(profissional -> {
                    Double precoProfissional = profissional.getTarifas();
                    if(precoProfissional >= faixaPrecoMin && precoProfissional <= faixaPrecoMax){
                        filtroTemp.add(e);
                    }

                });
            });

            estabelecimentoFiltrado = filtroTemp;
        }

        return estabelecimentoFiltrado;
    }
}
