package com.sub.techsub.infrastructure.repository;

import com.sub.techsub.core.domain.model.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IEstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {

}
