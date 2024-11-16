package com.sub.techsub.adapter.controller;

import com.sub.techsub.adapter.controller.resources.requests.ClienteRequest;
import com.sub.techsub.adapter.controller.resources.requests.ProfissionalRequest;
import com.sub.techsub.adapter.gateway.ClienteGateway;
import com.sub.techsub.core.domain.model.Cliente;
import com.sub.techsub.core.domain.model.Profissional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClienteController {

    @Autowired
    ClienteGateway clienteGateway;

    public Long registrarCliente(ClienteRequest clienteRequest){
        log.info("Cadastrando um novo cliente ...");
        Cliente cliente = new Cliente(clienteRequest);
        return clienteGateway.salvarCliente(cliente).getId();
    }

}
