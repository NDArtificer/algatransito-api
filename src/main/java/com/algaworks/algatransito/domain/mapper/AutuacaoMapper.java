package com.algaworks.algatransito.domain.mapper;

import com.algaworks.algatransito.api.model.input.AutuacaoInput;
import com.algaworks.algatransito.api.model.output.AutuacaoOutput;
import com.algaworks.algatransito.domain.model.Autuacao;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AutuacaoMapper {

    Autuacao toEntity(AutuacaoInput autuacaoInput);

    AutuacaoOutput toModel(Autuacao autuacao);
}
