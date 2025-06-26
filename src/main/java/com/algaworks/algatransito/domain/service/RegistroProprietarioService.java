package com.algaworks.algatransito.domain.service;

import com.algaworks.algatransito.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistroProprietarioService {

    @Autowired
    private ProprietarioRepository proprietarioRepository;

    @Transactional
    public Proprietario salvar(Proprietario proprietario) {
        boolean isEmailInUse = proprietarioRepository.findByEmail(proprietario.getEmail()).filter(p -> !p.equals(proprietario)).isPresent();
        if (isEmailInUse) {
            throw new NegocioException("Já existe um Proprietário cadastrado com o e-mail infomado!");
        } else {
            return proprietarioRepository.save(proprietario);
        }
    }

    public Proprietario buscar(Long id) {
        return proprietarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("O proprietário de id #%s não existe na base!", id)));
    }

    @Transactional
    public void excluir(Long proprietarioId) {
        proprietarioRepository.deleteById(proprietarioId);
    }

}
