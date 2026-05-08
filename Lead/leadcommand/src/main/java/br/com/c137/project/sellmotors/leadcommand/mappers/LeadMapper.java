package br.com.c137.project.sellmotors.leadcommand.mappers;

import br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.dtos.LeadDto;
import br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.models.LeadEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LeadMapper {

    LeadEntity leadDtoToEntity(LeadDto dto);

    LeadDto leadEntityToDto(LeadEntity entity);

    LeadEntity leadUpdate(LeadDto dto, @MappingTarget LeadEntity entity);
}
