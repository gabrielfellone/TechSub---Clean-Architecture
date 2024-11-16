package com.sub.techsub.adapter.controller.api;

import com.sub.techsub.adapter.controller.AgendamentoController;
import com.sub.techsub.adapter.controller.resources.responses.AgendamentoDisponibilidadeResource;
import com.sub.techsub.adapter.controller.resources.requests.AgendamentoRequest;
import com.sub.techsub.adapter.controller.resources.responses.AgendamentosRealizados;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequestMapping(value = "/api/agendamento")
@CrossOrigin
public class AgendamentoAPI {

    @Autowired
    AgendamentoController agendamentoController;

    @PostMapping
    public ResponseEntity<Long> realizarAgendamento(@RequestBody AgendamentoRequest agendamentoRequest) {
        log.info("Realizando um agendamento... {}", agendamentoRequest);
        return ResponseEntity.status(CREATED).body(agendamentoController.realizarAgendamento(agendamentoRequest));
    }

    @GetMapping
    public List<AgendamentosRealizados> listarTodos(){
        log.info("Listando todos os agendamentos realizados..");
        return agendamentoController.listarTodos();
    }
    @GetMapping("/disponibilidade/{id}")
    public AgendamentoDisponibilidadeResource
    verificaServicosProfissionaisDisponiveis(@PathVariable("id") Long id){
        log.info("Listando Profissionais e Servicos do Estabelecimento com ID {}", id);
        return agendamentoController.verificarServicosProfissionaisDisponiveis(id);
    }
}
