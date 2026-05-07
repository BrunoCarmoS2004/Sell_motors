package br.com.c137.project.sellmotors.authcommand.services;

import br.com.c137.project.sellmotors.authcommand.flyway.FlywayService;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.enums.DatabaseStatus;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.models.UserTenant;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.repositories.UserTenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserTenantService {
    @Autowired
    private UserTenantRepository userTenantRepository;

    @Autowired
    private FlywayService flywayService;

    @Value("${tenant.db.port}")
    private Integer port;

    @Value("${tenant.db.username}")
    private String username;

    @Value("${tenant.db.password}")
    private String dbPassword;

    public void createUserTenant(UUID dbUserId){
        UserTenant userTenant = new UserTenant(
                dbUserId,
                port,
                username,
                dbPassword
        );
        userTenant = userTenantRepository.save(userTenant);

        flywayService.createTenant(userTenant);
    }
    public void deleteUserTenant(UUID dbUserId){
        updateDataBaseStatus(DatabaseStatus.DELETED, dbUserId);
    }
    public void inactiveUserTenant(UUID dbUserId){
        updateDataBaseStatus(DatabaseStatus.INACTIVE, dbUserId);
    }
    public void returnToCreatedUserTenant(UUID dbUserId){
        updateDataBaseStatus(DatabaseStatus.CREATED, dbUserId);
    }
    protected void updateDataBaseStatus(DatabaseStatus databaseStatus, UUID id) {
        userTenantRepository.updateDatabaseStatus(DatabaseStatus.DELETED, id);
    }
}
