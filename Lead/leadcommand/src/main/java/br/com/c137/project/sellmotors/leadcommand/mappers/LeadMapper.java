package br.com.c137.project.sellmotors.leadcommand.mappers;

import br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.dtos.gets.LeadGetDto;
import br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.dtos.posts.LeadPostDto;
import br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.dtos.puts.LeadPutDto;
import br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.models.LeadEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LeadMapper {


    LeadEntity leadDtoPostToEntity(LeadPostDto dto);

    LeadGetDto leadEntityToDtoGet(LeadEntity entity);

    LeadEntity leadUpdatePut(LeadPutDto dto, @MappingTarget LeadEntity entity);
}
