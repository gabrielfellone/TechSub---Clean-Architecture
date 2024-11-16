package com.sub.techsub.adapter.gateway;

import com.sub.techsub.core.domain.model.Servico;
import com.sub.techsub.infrastructure.repository.IServicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoGateway {
    private final IServicosRepository servicoRepository;
    @Autowired
    public ServicoGateway(IServicosRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public Servico salvarServico(Servico servico) {
        return servicoRepository.save(servico);
    }

    public Optional<Servico> buscarServicoPorId(Long id) {
        return servicoRepository.findById(id);
    }
    public List<Servico> listarServicos() {
        return servicoRepository.findAll();
    }

    public Servico buscarServicoPorReference(Long servicoId) {
        return servicoRepository.getReferenceById(servicoId);
    }
}