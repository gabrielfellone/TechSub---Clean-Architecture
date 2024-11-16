package com.sub.techsub.adapter.controller.api;

import com.sub.techsub.adapter.controller.AvaliacaoController;
import com.sub.techsub.adapter.controller.resources.requests.AvaliacaoRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequestMapping(value = "/api/avaliacao")
@CrossOrigin
public class AvaliacaoAPI {

    @Autowired
    AvaliacaoController avaliacaoController;

    @PostMapping
    public ResponseEntity<Long> realizarAvalicao(@RequestBody AvaliacaoRequest avaliacaoRequest) {
        log.info("Realizando avaliacao... {}", avaliacaoRequest);
        return ResponseEntity.status(CREATED).body(avaliacaoController.realizarAvalicao(avaliacaoRequest));
    }

}
