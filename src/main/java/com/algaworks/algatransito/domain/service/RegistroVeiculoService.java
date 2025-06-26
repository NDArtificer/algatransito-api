package com.algaworks.algatransito.domain.service;

import com.algaworks.algatransito.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.model.StatusVeiculo;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import com.algaworks.algatransito.domain.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Optional;

import static com.algaworks.algatransito.domain.model.StatusVeiculo.REGULAR;

@Service
public class RegistroVeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private RegistroProprietarioService proprietarioService;


    public Veiculo buscar(Long id) {
        return veiculoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("O Veiculo de id #%s não existe na base!", id)));
    }

    @Transactional
    public Veiculo cadastrar(Veiculo veiculo) {

        if (veiculo.getId() != null) {
            throw new NegocioException("Veículo infomado não deve possuir um código!");
        }

        var isPlacaInUse = veiculoRepository.findByPlaca(veiculo.getPlaca())
                .filter(v -> !v.equals(veiculo))
                .isPresent();
        if (isPlacaInUse) {
            throw new NegocioException("Já existe uma placa cadastrada para o veículo infomado!");
        } else {

            var proprietario = proprietarioService.buscar(veiculo.getProprietario().getId());
            veiculo.setProprietario(proprietario);
            veiculo.setStatus(REGULAR);
            veiculo.setDataCadastro(OffsetDateTime.now());

            return veiculoRepository.save(veiculo);
        }
    }
}
