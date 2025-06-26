package com.algaworks.algatransito.api.model.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProprietarioInput {

    @NotBlank
    @Size(max = 255)
    private String nome;

    @NotBlank
    @Size(max = 255)
    @Email
    private String email;

    @NotBlank
    @Size(max = 25)
    private String telefone;
}
