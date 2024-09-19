package com.jrome.auth.config;

import com.jrome.auth.entities.Role;
import com.jrome.auth.repositories.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * RoleInitializer's only purpose is to add the ROLE_GARDEN_MASTER to the MySQL Database
 */
@Component
@RequiredArgsConstructor
public class RoleInitializer {

    private final RoleRepository roleRepository;

    @PostConstruct
    public void createRoleGardenMaster() {
        if (!roleRepository.existsByName("ROLE_GARDEN_MASTER")) {
            Role gardenMasterRole = new Role();
            gardenMasterRole.setName("ROLE_GARDEN_MASTER");
            roleRepository.save(gardenMasterRole);
        }
    }
}
