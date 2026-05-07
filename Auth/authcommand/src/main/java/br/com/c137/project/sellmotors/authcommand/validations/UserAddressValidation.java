package br.com.c137.project.sellmotors.authcommand.validations;

import br.com.c137.project.sellmotors.authcommand.exceptions.NotFoundException;
import br.com.c137.project.sellmotors.authcommand.exceptions.ValidationException;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.repositories.UserAddressRepository;
import br.com.c137.project.sellmotors.authcommand.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserAddressValidation {

    @Autowired
    private UserAddressRepository userAddressRepository;

    @Autowired
    private MessageUtils messageUtils;

    public void userAddressExistsValidation(UUID id){
        boolean exists = userAddressRepository.existsById(id);
        if (!exists) {
            throw new NotFoundException(messageUtils.getMessage("user.address.not-exists"));
        }
    }

    public void zipCodeAndNumberExistsValidation(String zipCode, Integer number){
        boolean exists = userAddressRepository.existsByZipCodeAndNumber(zipCode, number);
        if (exists){
            throw new ValidationException(getZipCodeAndNuberExistMessage());
        }
    }

    public void zipCodeAndNumberInOtherIdExistsValidation(String zipCode, Integer number, UUID id){
        boolean exists = userAddressRepository.existsByZipCodeAndNumberAndIdNot(zipCode, number, id);
        if (exists){
            throw new ValidationException(getZipCodeAndNuberExistMessage());
        }
    }

    private String getZipCodeAndNuberExistMessage(){
        return messageUtils.getMessage("user.address.zip-code-and-number-exists");
    }
}
