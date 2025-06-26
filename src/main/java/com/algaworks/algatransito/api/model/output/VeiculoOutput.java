package com.algaworks.algatransito.api.model.output;

import com.algaworks.algatransito.domain.model.StatusVeiculo;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Getter
@Setter
public class VeiculoOutput {

    @NotNull
    private Long id;

    @NotNull
    private ProprietarioOutput proprietario;

    @NotNull
    private String marca;

    @NotNull
    private String modelo;

    @NotNull
    private String placa;

    @NotNull
    private StatusVeiculo status;

    private OffsetDateTime dataCadastro;

    private OffsetDateTime dataApreensao;


}
