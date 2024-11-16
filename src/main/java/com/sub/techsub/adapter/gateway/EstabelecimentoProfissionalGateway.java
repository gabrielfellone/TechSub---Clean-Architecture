package com.sub.techsub.adapter.gateway;

import com.sub.techsub.core.domain.model.EstabelecimentoProfissional;
import com.sub.techsub.infrastructure.repository.IEstabelecimentoProfissionalRepository;
import org.springframework.stereotype.Service;

@Service
public class EstabelecimentoProfissionalGateway {

    private final IEstabelecimentoProfissionalRepository estabelecimentoProfissionalRepository;

    public EstabelecimentoProfissionalGateway(IEstabelecimentoProfissionalRepository estabelecimentoProfissionalRepository) {
        this.estabelecimentoProfissionalRepository = estabelecimentoProfissionalRepository;
    }

    public EstabelecimentoProfissional salvarRelacionamentoEstabelecimentoProfissional(EstabelecimentoProfissional estabelecimentoProfissional){
        return estabelecimentoProfissionalRepository.save(estabelecimentoProfissional);
    }
}
