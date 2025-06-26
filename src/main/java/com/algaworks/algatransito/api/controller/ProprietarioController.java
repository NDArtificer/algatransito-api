package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.api.model.input.ProprietarioInput;
import com.algaworks.algatransito.api.model.output.ProprietarioOutput;
import com.algaworks.algatransito.domain.mapper.ProprietarioMapper;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import com.algaworks.algatransito.domain.service.RegistroProprietarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/proprietarios")
public class ProprietarioController {

    @Autowired
    private ProprietarioRepository proprietarioRepository;

    @Autowired
    private RegistroProprietarioService proprietarioService;

    @Autowired
    private ProprietarioMapper proprietarioMapper;

    @GetMapping
    public ResponseEntity<List<ProprietarioOutput>> listar() {
        List<ProprietarioOutput> proprietarioOutputs = proprietarioRepository.findAll().stream().map(proprietarioMapper::toModel).toList();
        return ResponseEntity.ok(proprietarioOutputs);
    }

    @GetMapping("/{proprietarioId}")
    public ResponseEntity<ProprietarioOutput> buscar(@PathVariable Long proprietarioId) {
        return !proprietarioRepository.existsById(proprietarioId) ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(proprietarioMapper.toModel(proprietarioRepository.findById(proprietarioId).get()));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ProprietarioOutput adicionar(@Valid @RequestBody ProprietarioInput proprietarioInput) {
        Proprietario proprietario = proprietarioService.salvar(proprietarioMapper.toEntity(proprietarioInput));
        return proprietarioMapper.toModel(proprietario);
    }

    @PutMapping("/{proprietarioId}")
    public ResponseEntity<ProprietarioOutput> atualizar(@PathVariable Long proprietarioId,
                                                        @Valid @RequestBody ProprietarioInput proprietarioInput) {
        if (!proprietarioRepository.existsById(proprietarioId)) {
            return ResponseEntity.notFound().build();
        } else {
            var proprietario = proprietarioMapper.toEntity(proprietarioInput);
            proprietario.setId(proprietarioId);
            var proprietarioAutliazdo = proprietarioService.salvar(proprietario);
            return ResponseEntity.ok(proprietarioMapper.toModel(proprietarioAutliazdo));
        }
    }

    @DeleteMapping("/{proprietarioId}")
    public ResponseEntity<Void> remover(@PathVariable Long proprietarioId) {
        if (!proprietarioRepository.existsById(proprietarioId)) {
            return ResponseEntity.notFound().build();
        } else {
            proprietarioService.excluir(proprietarioId);
            return ResponseEntity.noContent().build();
        }
    }

}
