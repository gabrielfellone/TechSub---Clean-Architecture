package com.sub.techsub.infrastructure.repository;

import com.sub.techsub.core.domain.model.EstabelecimentoServico;
import com.sub.techsub.core.domain.model.EstabelecimentoServicoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEstabelecimentoServicoRepository extends JpaRepository<EstabelecimentoServico, EstabelecimentoServicoId> {

}
