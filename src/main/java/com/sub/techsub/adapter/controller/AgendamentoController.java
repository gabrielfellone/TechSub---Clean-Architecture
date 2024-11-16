package com.sub.techsub.adapter.controller;


import com.sub.techsub.adapter.controller.resources.requests.AgendamentoRequest;
import com.sub.techsub.adapter.controller.resources.responses.AgendamentoDisponibilidadeResource;
import com.sub.techsub.adapter.controller.resources.responses.AgendamentosRealizados;
import com.sub.techsub.adapter.gateway.*;
import com.sub.techsub.core.domain.model.*;
import com.sub.techsub.core.usecase.AgendamentoUseCases;
import com.sub.techsub.core.usecase.exception.AgendamentoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AgendamentoController {

    //Use cases ---

    @Autowired
    AgendamentoUseCases agendamentoUseCases;


    //Gateways para acessar o banco ---

    @Autowired
    EstabelecimentoGateway estabelecimentoGateway;

    @Autowired
    ProfissionalGateway profissionalGateway;

    @Autowired
    ClienteGateway clienteGateway;

    @Autowired
    AgendamentoGateway agendamentoGateway;

    @Autowired
    ServicoGateway servicoGateway;


    public Long realizarAgendamento(AgendamentoRequest agendamentoRequest) {

        LocalDateTime dataAgendamento = agendamentoRequest.getDataAgendamento();

        Optional<Estabelecimento> estabelecimento
                = estabelecimentoGateway.buscarEstabelecimentoPorId(agendamentoRequest.getEstabelecimento());

        Optional<Profissional> profissional
                = profissionalGateway.buscarProfissionalPorId(agendamentoRequest.getProfissional());


        agendamentoUseCases.validarDataEHoraEstabelecimento(estabelecimento, dataAgendamento);
        agendamentoUseCases.validarDataEHoraProfissional(profissional, dataAgendamento);

        log.info("Data e Hora validada, criando agendamento...");

        Agendamento agendamento = Agendamento.builder()
                .status("AGENDADO")
                .dataAgendamento(agendamentoUseCases.formatarDataParaAgendamento(dataAgendamento))
                .horaAgendamento(agendamentoUseCases.formatarHoraParaAgendamento(dataAgendamento))
                .profissional(profissionalGateway.buscarProfissionalPorReference(agendamentoRequest.getProfissional()))
                .estabelecimento(estabelecimentoGateway.buscarEstabelecimentoPorId(agendamentoRequest.getEstabelecimento()).get())
                .cliente(clienteGateway.buscarClientePorId(agendamentoRequest.getCliente()).get())
                .build();

        return agendamentoGateway.salvarAgendamento(agendamento).getId();

    }

    public List<AgendamentosRealizados> listarTodos(){
        log.info("Listando todos os agendamentos ");
        List<AgendamentosRealizados> agendamentosRealizados = new ArrayList<>();
        List<Agendamento> agendamentos = agendamentoGateway.buscaTodosAgendamentos();

        if(!agendamentos.isEmpty()){
            agendamentos.forEach(a ->{
                agendamentosRealizados.add(new AgendamentosRealizados(a));
            });
        }
        return agendamentosRealizados;

    }

    @Scheduled(fixedRate = 900000)  // a cada 15 minutos
    public void lembreteAgendamento() {
        log.info("Lembrete de agendamento iniciado...");

        List<Agendamento> agendamentos = agendamentoGateway.buscaTodosAgendamentosComoAgendado();

        log.info("Apenas agendamentos com status AGENDADO...");

        if(!agendamentos.isEmpty()){
            agendamentos.forEach(agendamento -> {
                //googleCalendario.criarAgendamento(agendamento);
            });
        }
    }

    public AgendamentoDisponibilidadeResource
    verificarServicosProfissionaisDisponiveis(Long idEstabelecimento){

        log.info("Verificando estabelecimento...");

        Optional<Estabelecimento> estabelecimento
                = estabelecimentoGateway.buscarEstabelecimentoPorId(idEstabelecimento);

        if(estabelecimento.isPresent()){
            List<Profissional> profissionais = new ArrayList<>();
            List<Servico> servicos = new ArrayList<>();
            estabelecimento.get().getProfissionais().forEach(prof -> {
                profissionais.add(profissionalGateway.buscarProfissionalPorId(prof.getProfissionalId()).get());
            });

            estabelecimento.get().getEstabelecimentoServicos().forEach(serv ->{
                servicos.add(servicoGateway.buscarServicoPorReference(serv.getServicoId()));
            });
            return AgendamentoDisponibilidadeResource.builder()
                    .profissionais(profissionais)
                    .servicos(servicos)
                    .build();
        } else throw new AgendamentoException("Estabelecimento nao encontrado, por favor, insira outro ID");

    }

}
