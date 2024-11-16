package com.sub.techsub.adapter.controller.resources.responses;



import com.sub.techsub.core.domain.model.Agendamento;
import com.sub.techsub.core.domain.model.Profissional;
import com.sub.techsub.core.domain.model.Servico;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgendamentosRealizados {

    private Profissional profissionais;
    private Servico servicos;
    private String endereco;
    private String nomeEstabelecimento;
    private String nomeCliente;
    private String statusAgendamento;

    public AgendamentosRealizados(Agendamento agendamento) {
        this.profissionais = agendamento.getProfissional();
        this.servicos = agendamento.getProfissional().getServico();
        this.endereco = agendamento.getEstabelecimento().getEndereco();
        this.nomeEstabelecimento = agendamento.getEstabelecimento().getNome();
        this.nomeCliente = agendamento.getCliente().getNome();
        this.statusAgendamento = agendamento.getStatus();
    }
}
