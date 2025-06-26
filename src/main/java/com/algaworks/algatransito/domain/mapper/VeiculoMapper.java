package com.algaworks.algatransito.domain.mapper;

import com.algaworks.algatransito.api.model.input.VeiculoInput;
import com.algaworks.algatransito.api.model.output.VeiculoOutput;
import com.algaworks.algatransito.domain.model.Veiculo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ProprietarioMapper.class)
public interface VeiculoMapper {

    VeiculoOutput toModel(Veiculo veiculo);
    Veiculo toEntity(VeiculoInput veiculoInput);

}
