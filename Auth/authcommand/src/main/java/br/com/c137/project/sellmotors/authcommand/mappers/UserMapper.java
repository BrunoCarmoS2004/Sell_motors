package br.com.c137.project.sellmotors.authcommand.mappers;

import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.dtos.gets.UserGetDTO;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.dtos.posts.UserPostDTO;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.dtos.puts.UserPutDTO;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserGetDTO userToUserGetDTO(User user);
    User postToUser(UserPostDTO userPostDTO);
    User putToUser(UserPutDTO userPutDTO, @MappingTarget User user);
}
