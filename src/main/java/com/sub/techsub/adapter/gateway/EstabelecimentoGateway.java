package com.sub.techsub.adapter.gateway;

import com.sub.techsub.core.domain.model.Estabelecimento;
import com.sub.techsub.infrastructure.repository.IEstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstabelecimentoGateway {

    private final IEstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    public EstabelecimentoGateway(IEstabelecimentoRepository estabelecimentoRepository) {
        this.estabelecimentoRepository = estabelecimentoRepository;
    }

    public Estabelecimento salvarEstabelecimento(Estabelecimento estabelecimento) {
        return estabelecimentoRepository.save(estabelecimento);
    }

    public Optional<Estabelecimento> buscarEstabelecimentoPorId(Long id) {
        return estabelecimentoRepository.findById(id);
    }

    public List<Estabelecimento> listarEstabelecimentos() {
        return estabelecimentoRepository.findAll();
    }

}