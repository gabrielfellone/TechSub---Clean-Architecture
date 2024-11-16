package com.sub.techsub.adapter.gateway;

import com.sub.techsub.core.domain.model.Profissional;
import com.sub.techsub.infrastructure.repository.IProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfissionalGateway {

    private final IProfissionalRepository profissionalRepository;

    @Autowired
    public ProfissionalGateway(IProfissionalRepository profissionalRepository) {
        this.profissionalRepository = profissionalRepository;
    }

    public Profissional salvarProfissional(Profissional profissional) {
        return profissionalRepository.save(profissional);
    }

    public Optional<Profissional> buscarProfissionalPorId(Long id) {
        return profissionalRepository.findById(id);
    }

    public Profissional buscarProfissionalPorReference(Long id){
        return profissionalRepository.getReferenceById(id);
    }

    public List<Profissional> listarProfissionais() {
        return profissionalRepository.findAll();
    }


}