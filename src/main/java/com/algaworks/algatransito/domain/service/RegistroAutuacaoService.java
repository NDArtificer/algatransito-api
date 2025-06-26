package com.algaworks.algatransito.domain.service;

import com.algaworks.algatransito.domain.model.Autuacao;
import com.algaworks.algatransito.domain.model.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistroAutuacaoService {

    @Autowired
    private RegistroVeiculoService veiculoService;

    public Autuacao registar(Long veiculoId, Autuacao autuacao) {
        Veiculo veiculo = veiculoService.buscar(veiculoId);
        return veiculo.adicionarAutuacao(autuacao);

    }
}
