package com.sub.techsub.adapter.gateway;

import com.sub.techsub.core.domain.model.EstabelecimentoServico;
import com.sub.techsub.infrastructure.repository.IEstabelecimentoServicoRepository;
import org.springframework.stereotype.Service;

@Service
public class EstabelecimentoServicoGateway {

    private final IEstabelecimentoServicoRepository estabelecimentoServicoRepository;

    public EstabelecimentoServicoGateway(IEstabelecimentoServicoRepository estabelecimentoServicoRepository) {
        this.estabelecimentoServicoRepository = estabelecimentoServicoRepository;
    }

    public EstabelecimentoServico salvarRelacionamentoEstabelecimentoServico(EstabelecimentoServico estabelecimentoServico){
        return estabelecimentoServicoRepository.save(estabelecimentoServico);

    }


}
