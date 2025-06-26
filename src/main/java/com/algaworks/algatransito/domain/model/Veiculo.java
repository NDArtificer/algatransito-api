package com.algaworks.algatransito.domain.model;

import com.algaworks.algatransito.domain.exception.NegocioException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.algaworks.algatransito.domain.model.StatusVeiculo.APREENDIDO;
import static com.algaworks.algatransito.domain.model.StatusVeiculo.REGULAR;
import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Veiculo {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    private Proprietario proprietario;

    private String marca;

    private String modelo;

    private String placa;

    @Enumerated(EnumType.STRING)
    private StatusVeiculo status;

    private OffsetDateTime dataCadastro;

    private OffsetDateTime dataApreensao;

    @OneToMany(mappedBy = "veiculo", cascade = ALL)
    private List<Autuacao> autuacoes = new ArrayList<>();

    public Autuacao adicionarAutuacao(Autuacao autuacao){
        autuacao.setDataOcorrencia(OffsetDateTime.now());
        autuacao.setVeiculo(this);
        getAutuacoes().add(autuacao);
        return autuacao;
    }

    public void apreender() {
        if(isApreendido()){
            throw new NegocioException("Veiculo já está apreendido!");
        }
        setStatus(APREENDIDO);
        setDataApreensao(OffsetDateTime.now());
    }

    public void liberar(){
        if(isNotApreendido()){
            throw new NegocioException("Veiculo não está apreendido!");
        }
        setStatus(REGULAR);
        setDataApreensao(null);
    }

    public boolean isApreendido(){
        return getStatus().equals(APREENDIDO);
    }

    private boolean isNotApreendido() {
        return !isApreendido();
    }
}
