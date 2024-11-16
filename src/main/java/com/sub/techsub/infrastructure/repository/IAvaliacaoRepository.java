package com.sub.techsub.infrastructure.repository;

import com.sub.techsub.core.domain.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface IAvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

}
