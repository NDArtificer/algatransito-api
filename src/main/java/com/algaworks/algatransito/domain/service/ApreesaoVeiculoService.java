package com.algaworks.algatransito.domain.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApreesaoVeiculoService {

    @Autowired
    private RegistroVeiculoService veiculoService;

    @Transactional
    public void apreenderVeiculo(Long id) {
        var veiculo = veiculoService.buscar(id);
        veiculo.apreender();
    }

    @Transactional
    public void liberarVeiculo(Long id) {
        var veiculo = veiculoService.buscar(id);
        veiculo.liberar();
    }

}
