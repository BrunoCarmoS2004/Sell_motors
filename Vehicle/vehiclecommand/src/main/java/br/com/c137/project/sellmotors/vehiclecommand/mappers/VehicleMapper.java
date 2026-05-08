package br.com.c137.project.sellmotors.vehiclecommand.mappers;

import br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.dtos.gets.VehicleGetDTO;
import br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.dtos.posts.VehiclePostDTO;
import br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.dtos.puts.VehiclePutDTO;
import br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.models.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
    VehicleGetDTO vehicleToVehicleGetDTO(Vehicle vehicle);
    Vehicle postToVehicle(VehiclePostDTO vehiclePostDTO);
    Vehicle putToVehicle(VehiclePutDTO vehiclePutDTO, @MappingTarget Vehicle vehicle);
}
