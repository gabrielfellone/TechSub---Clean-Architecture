package com.sub.techsub.infrastructure.repository;

import com.sub.techsub.core.domain.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IProfissionalRepository extends JpaRepository<Profissional, Long> {

}
