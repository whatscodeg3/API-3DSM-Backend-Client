package com.br.WhatsCodeClientMicroservice.mapper;

import com.br.WhatsCodeClientMicroservice.models.Address;
import com.br.WhatsCodeClientMicroservice.models.ViaCepAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddresMapper {

    @Mapping(target = "publicPlace", source = "logradouro")
//    @Mapping(target = "complement", source = "complemento")
    @Mapping(target = "neighborhood", source = "bairro")
    @Mapping(target = "city", source = "localidade")
    @Mapping(target = "state", source = "uf")
    Address mapToViaCep(ViaCepAddress viaCepAddress);

}
