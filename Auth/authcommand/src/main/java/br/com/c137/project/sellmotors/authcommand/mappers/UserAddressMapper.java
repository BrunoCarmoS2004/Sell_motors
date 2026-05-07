package br.com.c137.project.sellmotors.authcommand.mappers;


import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.dtos.gets.UserAddressGetDTO;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.dtos.posts.UserAddressPostDTO;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.dtos.puts.UserAddressPutDTO;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.models.UserAddress;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserAddressMapper {
    UserAddressGetDTO userAddressToUserUserAddressGetDTO(UserAddress address);
    UserAddress postToUserAddress(UserAddressPostDTO address);
    UserAddress putToUserAddress(UserAddressPutDTO addressPutDTO, @MappingTarget UserAddress address);
}
