package com.algaworks.algatransito.domain.mapper;

import com.algaworks.algatransito.api.model.input.ProprietarioInput;
import com.algaworks.algatransito.api.model.input.ProprietarioSumaryInput;
import com.algaworks.algatransito.api.model.output.ProprietarioOutput;
import com.algaworks.algatransito.domain.model.Proprietario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProprietarioMapper {

    ProprietarioOutput toModel(Proprietario proprietario);
    Proprietario toEntity(ProprietarioInput proprietarioInput);
    Proprietario toEntity(ProprietarioSumaryInput proprietarioSumaryInput);
}
