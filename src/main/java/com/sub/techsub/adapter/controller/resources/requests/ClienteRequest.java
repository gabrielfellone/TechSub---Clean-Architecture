package com.sub.techsub.adapter.controller.resources.requests;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteRequest {

    @Schema(description = "Nome completo do cliente", example = "Gabriel Petruf")
    private String nome;
}
