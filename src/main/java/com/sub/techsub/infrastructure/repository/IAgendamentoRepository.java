package com.sub.techsub.infrastructure.repository;

import com.sub.techsub.core.domain.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IAgendamentoRepository extends JpaRepository<Agendamento, Long> {

}
