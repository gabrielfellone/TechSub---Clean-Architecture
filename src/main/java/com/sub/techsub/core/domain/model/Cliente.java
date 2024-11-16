package com.sub.techsub.core.domain.model;

import com.sub.techsub.adapter.controller.resources.requests.ClienteRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nome;

    public Cliente(ClienteRequest clienteRequest) {
        nome = clienteRequest.getNome();
    }
}