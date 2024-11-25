package com.sub.techsub.core.domain.model;

import com.sub.techsub.adapter.controller.resources.requests.EstabelecimentoRequest;
import com.sub.techsub.core.domain.presenter.FormatarValorFotosEstacionamento;
import jakarta.persistence.*;
import java.util.List;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;


@Entity
@Table(name = "estabelecimento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(nullable = false, length = 255)
    private String endereco;

    @Column(name = "horario_funcionamento", length = 255)
    private String horarioFuncionamento;

    @Convert(converter = FormatarValorFotosEstacionamento.class)
    @Column(name = "fotos")
    private String[] fotos;

    @ManyToOne
    @JoinColumn(name = "avaliacao_id")
    private Avaliacao avaliacao;

    @OneToMany(mappedBy = "estabelecimento")
    private List<EstabelecimentoProfissional> profissionais;

    @OneToMany(mappedBy = "estabelecimento")
    private List<EstabelecimentoServico> estabelecimentoServicos;


    public Estabelecimento(EstabelecimentoRequest request) {
        nome = request.getNome();
        endereco = request.getEndereco();
        horarioFuncionamento = request.getHorarioFuncionamento();
        fotos = request.getFotos().toArray(new String[0]);
    }
}