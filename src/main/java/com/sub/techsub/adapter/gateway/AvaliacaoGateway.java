package com.sub.techsub.adapter.gateway;

import com.sub.techsub.core.domain.model.Avaliacao;
import com.sub.techsub.infrastructure.repository.IAvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AvaliacaoGateway {

    private final IAvaliacaoRepository avaliacaoRepository;

    @Autowired
    public AvaliacaoGateway(IAvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }
    @Transactional
    public Avaliacao salvarAvaliacao(Avaliacao avaliacao) {
        return avaliacaoRepository.save(avaliacao);
    }
    public Optional<Avaliacao> buscarAvaliacaoPorId(Long id) {
        return avaliacaoRepository.findById(id);
    }
    public void flush() {
        avaliacaoRepository.flush();
    }
}