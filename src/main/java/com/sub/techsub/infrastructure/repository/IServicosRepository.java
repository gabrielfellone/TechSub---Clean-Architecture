package com.sub.techsub.infrastructure.repository;

import com.sub.techsub.core.domain.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IServicosRepository extends JpaRepository<Servico, Long> {

}
