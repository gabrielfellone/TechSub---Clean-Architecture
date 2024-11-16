package com.sub.techsub.adapter.controller;

import com.sub.techsub.adapter.gateway.ServicoGateway;
import com.sub.techsub.core.domain.model.Servico;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ServicoController {

    //Gateways para acessar o banco ---
    @Autowired
    ServicoGateway servicoGateway;

    public Servico buscaPorIdServico(Long id) {
        log.info("Buscando o servico por ID ..");
        return servicoGateway.buscarServicoPorReference(id);
    }

}
