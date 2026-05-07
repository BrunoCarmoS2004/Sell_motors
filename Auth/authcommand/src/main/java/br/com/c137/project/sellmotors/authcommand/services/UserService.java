package br.com.c137.project.sellmotors.authcommand.services;

import br.com.c137.project.sellmotors.authcommand.exceptions.NotFoundException;
import br.com.c137.project.sellmotors.authcommand.exceptions.UnauthorizedException;
import br.com.c137.project.sellmotors.authcommand.mappers.UserMapper;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.dtos.gets.UserGetDTO;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.dtos.gets.UserTokenGetDTO;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.dtos.posts.UserPostDTO;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.dtos.puts.UserPutDTO;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.enums.CreationStatus;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.enums.DatabaseStatus;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.enums.EntityStatus;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.models.User;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.repositories.UserRepository;
import br.com.c137.project.sellmotors.authcommand.utils.MessageUtils;
import br.com.c137.project.sellmotors.authcommand.validations.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserValidation userValidation;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserTenantService userTenantService;

    @Autowired
    private MessageUtils messageUtils;

    public UserTokenGetDTO getUserByEmailOrInscription(String loginEmail, String loginInscription, String loginPassword) {
        UserTokenGetDTO userTokenGetDTO = userRepository.findByEmailOrInscription(loginEmail, loginInscription).orElseThrow(
                () -> new UnauthorizedException("Invalid Credencials"));
        userValidation.passwordEncoderMatches(loginPassword, userTokenGetDTO.password());
        userRepository.updateLastAcess(LocalDateTime.now(), userTokenGetDTO.id());
        return userTokenGetDTO;
    }

    @Cacheable(value = "users", key = "#pageable.pageNumber + '-' + #pageable.pageSize")
    public PagedModel<UserGetDTO> getAll(Pageable pageable) {
        Page<User> page = userRepository.findAll(pageable);
        Page<UserGetDTO> users = page.map(userMapper::userToUserGetDTO);
        return new PagedModel<>(users);
    }

    @Cacheable(value = "user", key = "#id")
    public UserGetDTO getUserById(UUID id) {
        return userRepository.findById(id, UserGetDTO.class).orElseThrow(() -> new NotFoundException(getNotFoundMessage()));
    }

    @CacheEvict(value = "users", allEntries = true)
    public UserGetDTO postUser(UserPostDTO userPostDTO) {
        userValidation.inscriptionExistsValidation(userPostDTO.inscription());
        userValidation.emailExistsValidation(userPostDTO.email());
        User user = userMapper.postToUser(userPostDTO);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user = userRepository.save(user);
        userTenantService.createUserTenant(user.getId());
        updateDataBaseStuatus(user.getId());
        return userMapper.userToUserGetDTO(user);
    }

    @Caching(evict = {
            @CacheEvict(value = "users", allEntries = true),
            @CacheEvict(value = "user", key = "#id")
    })
    public UserGetDTO putUser(UUID id, UserPutDTO userPutDTO) {
        userValidation.inscriptionExistsInOtherIdValidation(userPutDTO.inscription(), id);
        userValidation.emailExistsInOtherIdValidation(userPutDTO.email(), id);
        User user = userRepository.findById(id, User.class).orElseThrow(() -> new NotFoundException(getNotFoundMessage()));
        user = userMapper.putToUser(userPutDTO, user);
        user = userRepository.save(user);

        return userMapper.userToUserGetDTO(user);
    }

    @Caching(evict = {
            @CacheEvict(value = "users", allEntries = true),
            @CacheEvict(value = "user", key = "#id")
    })
    public void deleteUser(UUID id) {
        userExistsValidation(id);
        updateEntityStatus(EntityStatus.DELETED, id);
    }

    @Caching(evict = {
            @CacheEvict(value = "users", allEntries = true),
            @CacheEvict(value = "user", key = "#id")
    })
    public void inactiveUser(UUID id) {
        userExistsValidation(id);
        updateEntityStatus(EntityStatus.INACTIVE, id);
    }

    protected void updateCreationStuatus(UUID id) {
        userRepository.updateCreationStatus(CreationStatus.REGISTERED, id);
    }

    protected void updateDataBaseStuatus(UUID id) {
        userRepository.updateDataBaseStuatus(DatabaseStatus.CREATED, id);
    }

    protected void userExistsValidation(UUID id) {
        userValidation.userExistsValidation(id);
    }

    protected void updateEntityStatus(EntityStatus entityStatus, UUID id) {
        userRepository.updateEntityStatus(entityStatus, id);
    }

    private String getNotFoundMessage(){
        return messageUtils.getMessage("user.not-found");
    }
}
