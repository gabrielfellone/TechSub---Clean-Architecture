package com.sub.techsub.adapter.controller.resources.responses;


import com.sub.techsub.core.domain.model.Avaliacao;
import com.sub.techsub.core.domain.model.Profissional;
import com.sub.techsub.core.domain.model.Servico;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstabelecimentoResource {

    private String nome;

    private String endereco;

    private List<Servico> servicos;

    private List<Profissional> profissionals;

    private String horarioFuncionamento;

    private List<String> fotos;

    private Avaliacao avaliacao;

}
