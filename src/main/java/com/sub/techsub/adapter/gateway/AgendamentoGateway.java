package com.sub.techsub.adapter.gateway;

import com.sub.techsub.core.domain.model.Agendamento;
import com.sub.techsub.infrastructure.repository.IAgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoGateway {

    private final IAgendamentoRepository agendamentoRepository;

    @Autowired
    public AgendamentoGateway(IAgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }
    @Transactional
    public Agendamento salvarAgendamento(Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }
    public Optional<Agendamento> buscarAgendamentoPorId(Long id) {
        return agendamentoRepository.findById(id);
    }

    public List<Agendamento> buscaTodosAgendamentos(){
        return agendamentoRepository.findAll();
    }

    public List<Agendamento> buscaTodosAgendamentosComoAgendado(){
        return agendamentoRepository.findAll()
                .stream()
                .filter(agendamento -> agendamento.getStatus().equalsIgnoreCase("AGENDADO"))
                .toList();
    }

    public void deletarAgendamento(Agendamento agendamento){
        agendamentoRepository.delete(agendamento);
    }

    public void flush() {
        agendamentoRepository.flush();
    }
}
