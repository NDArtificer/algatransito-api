package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.api.model.input.AutuacaoInput;
import com.algaworks.algatransito.api.model.output.AutuacaoOutput;
import com.algaworks.algatransito.domain.mapper.AutuacaoMapper;
import com.algaworks.algatransito.domain.service.RegistroAutuacaoService;
import com.algaworks.algatransito.domain.service.RegistroVeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/veiculos/{veiculoId}/autuacoes")
public class AutuacaoController {

    @Autowired
    private RegistroAutuacaoService autuacaoService;

    @Autowired
    private RegistroVeiculoService veiculoService;

    @Autowired
    private AutuacaoMapper autuacaoMapper;

    @GetMapping
    public List<AutuacaoOutput> listar(@PathVariable Long veiculoId){
        var veiculo = veiculoService.buscar(veiculoId);
        return veiculo.getAutuacoes().stream().map(autuacaoMapper::toModel).toList();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public AutuacaoOutput registrarAutuacao(@PathVariable Long veiculoId, @RequestBody AutuacaoInput autuacaoInput){
        var autuacao = autuacaoMapper.toEntity(autuacaoInput);
        var autuacaoRegistrada = autuacaoService.registar(veiculoId, autuacao);
        return autuacaoMapper.toModel(autuacaoRegistrada);
    }
}
