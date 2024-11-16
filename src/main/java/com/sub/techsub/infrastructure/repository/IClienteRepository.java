package com.sub.techsub.infrastructure.repository;

import com.sub.techsub.core.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IClienteRepository extends JpaRepository<Cliente, Long> {

}
