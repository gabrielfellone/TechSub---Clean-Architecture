package com.sub.techsub.adapter.controller;

import com.sub.techsub.adapter.controller.resources.requests.ProfissionalRequest;
import com.sub.techsub.adapter.gateway.ProfissionalGateway;
import com.sub.techsub.adapter.gateway.ServicoGateway;
import com.sub.techsub.core.domain.model.Profissional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProfissionalController {


    //Gateways para acessar o banco ---

    @Autowired
    ProfissionalGateway profissionalGateway;

    @Autowired
    ServicoGateway servicoGateway;

    public Optional<Profissional> buscarPerfilProfissional(Long id){
        log.info("Buscando o profissional por ID ..");
        return profissionalGateway.buscarProfissionalPorId(id);
    }
    public Profissional buscaPorIdProfissional(Long id){
        return profissionalGateway.buscarProfissionalPorReference(id);
    }
    public Long cadastrarProfissional(ProfissionalRequest profissionalRequest){
        log.info("Cadastrando um novo profissional ...");
        Profissional profissional = new Profissional(profissionalRequest);
        profissional.setServico(servicoGateway.buscarServicoPorReference(profissionalRequest.getServico()));
        return profissionalGateway.salvarProfissional(profissional).getId();
    }

    public List<Profissional> listarTodos() {
        log.info("Buscando todos os Profissionais na base");
        return profissionalGateway.listarProfissionais();
    }

    public void atualizaProfissional(Profissional profissional){
        log.info("Atualizando o Profissional {} na base", profissional);
        profissionalGateway.salvarProfissional(profissional);
    }
}
