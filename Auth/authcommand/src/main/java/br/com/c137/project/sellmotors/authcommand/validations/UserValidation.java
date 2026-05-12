package br.com.c137.project.sellmotors.authcommand.validations;

import br.com.c137.project.sellmotors.authcommand.exceptions.NotFoundException;
import br.com.c137.project.sellmotors.authcommand.exceptions.UnauthorizedException;
import br.com.c137.project.sellmotors.authcommand.exceptions.ValidationException;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.repositories.UserRepository;
import br.com.c137.project.sellmotors.authcommand.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserValidation {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageUtils messageUtils;

    public void passwordEncoderMatches(String loginPassword, String dbUserPassword) {
        if (!passwordEncoder.matches(loginPassword, dbUserPassword)) {
            throw new UnauthorizedException("Invalid Credencials");
        }
    }

    public void userExistsValidation(UUID id) {
        boolean exist = userRepository.existsById(id);
        if (!exist) {
            throw new NotFoundException(messageUtils.getMessage("user.not-exists"));
        }
    }

    public void inscriptionExistsValidation(String inscription) {
        boolean exist = userRepository.existsByInscription(inscription);
        if (exist) {
            throw new ValidationException(getInscriptionExistsMessage());
        }
    }

    public void emailExistsValidation(String email) {
        boolean exist = userRepository.existsByEmail(email);
        if (exist) {
            throw new ValidationException(getEmailExistsMessage());
        }
    }

    public void inscriptionExistsInOtherIdValidation(String inscription, UUID id) {
        boolean exist = userRepository.existsByInscriptionAndIdNot(inscription, id);
        if (exist) {
            throw new ValidationException(getInscriptionExistsMessage());
        }
    }

    public void emailExistsInOtherIdValidation(String email, UUID id) {
        boolean exist = userRepository.existsByEmailAndIdNot(email, id);
        if (exist) {
            throw new ValidationException(getEmailExistsMessage());
        }
    }

    private String getEmailExistsMessage(){
        return messageUtils.getMessage("user.email-exists");
    }
    private String getInscriptionExistsMessage(){
        return messageUtils.getMessage("user.inscription-exists");
    }
}
