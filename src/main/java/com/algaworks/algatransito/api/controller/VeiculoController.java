package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.api.model.input.VeiculoInput;
import com.algaworks.algatransito.api.model.output.VeiculoOutput;
import com.algaworks.algatransito.domain.mapper.VeiculoMapper;
import com.algaworks.algatransito.domain.repository.VeiculoRepository;
import com.algaworks.algatransito.domain.service.ApreesaoVeiculoService;
import com.algaworks.algatransito.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private RegistroVeiculoService veiculoService;

    @Autowired
    private ApreesaoVeiculoService apreesaoVeiculoService;

    @Autowired
    private VeiculoMapper veiculoMapper;

    @GetMapping
    public List<VeiculoOutput> listar() {
        return veiculoRepository.findAll().stream().map(veiculoMapper::toModel).toList();

    }

    @GetMapping("/{veiculoId}")
    public ResponseEntity<VeiculoOutput> buscar(@PathVariable Long veiculoId){
        var veiculo =  veiculoRepository.findById(veiculoId);
        return veiculo.map(value -> ResponseEntity.ok(veiculoMapper.toModel(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public VeiculoOutput casdastrar(@Valid @RequestBody VeiculoInput veiculoInput){
        var veiculo = veiculoMapper.toEntity(veiculoInput);
        return veiculoMapper.toModel(veiculoService.cadastrar(veiculo));
    }

    @PutMapping("/{veiculoId}/apreensao")
    @ResponseStatus(NO_CONTENT)
    public void apreender(@PathVariable Long veiculoId){
        apreesaoVeiculoService.apreenderVeiculo(veiculoId);
    }

    @DeleteMapping("/{veiculoId}/apreensao")
    @ResponseStatus(NO_CONTENT)
    public void liberar(@PathVariable Long veiculoId){
        apreesaoVeiculoService.liberarVeiculo(veiculoId);
    }

}
