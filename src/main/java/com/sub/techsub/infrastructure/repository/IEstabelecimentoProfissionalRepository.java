package com.sub.techsub.infrastructure.repository;

import com.sub.techsub.core.domain.model.EstabelecimentoProfissional;
import com.sub.techsub.core.domain.model.EstabelecimentoProfissionalId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEstabelecimentoProfissionalRepository extends JpaRepository<EstabelecimentoProfissional, EstabelecimentoProfissionalId> {

}
