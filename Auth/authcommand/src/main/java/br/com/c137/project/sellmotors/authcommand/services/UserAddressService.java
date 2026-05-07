package br.com.c137.project.sellmotors.authcommand.services;


import br.com.c137.project.sellmotors.authcommand.exceptions.NotFoundException;
import br.com.c137.project.sellmotors.authcommand.mappers.UserAddressMapper;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.dtos.gets.UserAddressGetDTO;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.dtos.posts.UserAddressPostDTO;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.dtos.puts.UserAddressPutDTO;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.enums.EntityStatus;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.models.UserAddress;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.repositories.UserAddressRepository;
import br.com.c137.project.sellmotors.authcommand.utils.MessageUtils;
import br.com.c137.project.sellmotors.authcommand.validations.UserAddressValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserAddressService {
    @Autowired
    private UserAddressRepository userAddressRepository;

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Autowired
    private UserAddressValidation userAddressValidation;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageUtils messageUtils;

    @Cacheable(value = "userAddresses", key = "#pageable.pageNumber + '-' + #pageable.pageSize")
    public PagedModel<UserAddressGetDTO> getAll(Pageable pageable) {
        Page<UserAddress> page = userAddressRepository.findAll(pageable);
        Page<UserAddressGetDTO> userAddresses = page.map(userAddressMapper::userAddressToUserUserAddressGetDTO);
        return new PagedModel<>(userAddresses);
    }

    @Cacheable(value = "userAddressesOf", key = "#id.toString() + '-' + #pageable.pageNumber + '-' + #pageable.pageSize")
    public PagedModel<UserAddressGetDTO> getAllByUserAddressOf(UUID id, Pageable pageable) {
        Page<UserAddress> page = userAddressRepository.findAllByAddressOf(id, pageable);
        Page<UserAddressGetDTO> userAddresses = page.map(userAddressMapper::userAddressToUserUserAddressGetDTO);
        return new PagedModel<>(userAddresses);
    }

    @Cacheable(value = "userAddress", key = "#id")
    public UserAddressGetDTO getUserAddressById(UUID id) {
        return userAddressRepository.findById(id, UserAddressGetDTO.class)
                .orElseThrow(() -> new NotFoundException(getNotFoundMessage()));
    }

    @CacheEvict(value = {"userAddresses"}, allEntries = true)
    public UserAddressGetDTO postUserAddress(UserAddressPostDTO userAddressPostDTO) {
        userAddressValidation.zipCodeAndNumberExistsValidation(userAddressPostDTO.zipCode(), userAddressPostDTO.number());
        existsCreatedForEntity(userAddressPostDTO);

        UserAddress userAddress = userAddressMapper.postToUserAddress(userAddressPostDTO);
        userAddress = userAddressRepository.save(userAddress);

        updateCreatedForEntityStatus(userAddressPostDTO);
        return userAddressMapper.userAddressToUserUserAddressGetDTO(userAddress);
    }

    @Caching(evict = {
            @CacheEvict(value = "userAddresses", allEntries = true),
            @CacheEvict(value = "userAddressesOf", allEntries = true),
            @CacheEvict(value = "userAddress", key = "#id")
    })
    public UserAddressGetDTO putUserAddress(UUID id, UserAddressPutDTO userAddressPutDTO) {
        userAddressValidation.zipCodeAndNumberInOtherIdExistsValidation(userAddressPutDTO.zipCode(), userAddressPutDTO.number(), id);

        UserAddress userAddress = userAddressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(getNotFoundMessage()));

        userAddress = userAddressMapper.putToUserAddress(userAddressPutDTO, userAddress);
        userAddress = userAddressRepository.save(userAddress);

        return userAddressMapper.userAddressToUserUserAddressGetDTO(userAddress);
    }

    @Caching(evict = {
            @CacheEvict(value = "userAddresses", allEntries = true),
            @CacheEvict(value = "userAddressesOf", allEntries = true),
            @CacheEvict(value = "userAddress", key = "#id")
    })
    public void deleteUserAddress(UUID id) {
        userAddressValidation.userAddressExistsValidation(id);
        userAddressRepository.updateEntityStatus(EntityStatus.DELETED, id);
    }

    @Caching(evict = {
            @CacheEvict(value = "userAddresses", allEntries = true),
            @CacheEvict(value = "userAddressesOf", allEntries = true),
            @CacheEvict(value = "userAddress", key = "#id")
    })
    public void inactiveUserAddress(UUID id) {
        userAddressValidation.userAddressExistsValidation(id);
        userAddressRepository.updateEntityStatus(EntityStatus.INACTIVE, id);
    }

    private void updateCreatedForEntityStatus(UserAddressPostDTO userAddressPostDTO) {
        userService.updateCreationStuatus(userAddressPostDTO.addressOf());
    }

    private void existsCreatedForEntity(UserAddressPostDTO userAddressPostDTO) {
        userService.userExistsValidation(userAddressPostDTO.addressOf());
    }

    private String getNotFoundMessage(){
        return messageUtils.getMessage("user.address.not-found");
    }
}
