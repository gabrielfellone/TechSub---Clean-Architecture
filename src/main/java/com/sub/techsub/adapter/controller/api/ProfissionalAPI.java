package com.sub.techsub.adapter.controller.api;

import com.sub.techsub.adapter.controller.ProfissionalController;
import com.sub.techsub.adapter.controller.resources.requests.ProfissionalRequest;
import com.sub.techsub.core.domain.model.Profissional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequestMapping(value = "/api/profissional")
@CrossOrigin
public class ProfissionalAPI {

    @Autowired
    ProfissionalController profissionalController;

    @PostMapping
    public ResponseEntity<Long> cadastrar(@RequestBody ProfissionalRequest profissional) {
        log.info("Cadastrando um novo profissional... {}", profissional);
        return ResponseEntity.status(CREATED).body(profissionalController.cadastrarProfissional(profissional));
    }

    @GetMapping
    public List<Profissional> listarTodos(){
        log.info("Listando todos os profissionais");
        return profissionalController.listarTodos();
    }

    @GetMapping("/perfil/{id}")
    public Optional<Profissional> buscarPerfilProfissional(@PathVariable("id") Long id){
        log.info("Mostrando o perfil do usuario {} ", id);
        return profissionalController.buscarPerfilProfissional(id);
    }

}
