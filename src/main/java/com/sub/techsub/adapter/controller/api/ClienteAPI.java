package com.sub.techsub.adapter.controller.api;

import com.sub.techsub.adapter.controller.ClienteController;
import com.sub.techsub.adapter.controller.resources.requests.ClienteRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequestMapping(value = "/api/cliente")
@CrossOrigin
public class ClienteAPI {

    @Autowired
    ClienteController clienteController;

    @PostMapping
    public ResponseEntity<Long> registrarCliente(@RequestBody ClienteRequest clienteRequest) {
        log.info("Realizando o registro do cliente... {}", clienteRequest);
        return ResponseEntity.status(CREATED).body(clienteController.registrarCliente(clienteRequest));
    }

}
