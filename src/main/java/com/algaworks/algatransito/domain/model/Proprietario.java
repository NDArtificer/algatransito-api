package com.algaworks.algatransito.domain.model;

import com.algaworks.algatransito.domain.validation.ValidationGroups;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Proprietario {

    @Id
    @NotNull(groups = ValidationGroups.Proprietarioid.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column
    private String nome;

    @Column
    private String email;

    @Column
    private String telefone;

}
