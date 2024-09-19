package com.jromeo.webservice.auth.config;

import com.jromeo.webservice.auth.entity.RoleEntity;
import com.jromeo.webservice.auth.entity.UserEntity;
import com.jromeo.webservice.auth.repository.RoleRepository;
import com.jromeo.webservice.auth.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Author: Johan Romeo
 */

@Component
@RequiredArgsConstructor
public class RoleInitializer {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Method that creates a new user with the master admin role, ROLE_ADMIN_ROOT.
     * This simplifies the start-up.
     */
    @PostConstruct
    public void createAdminWithRootAdminRole() {

        if (!roleRepository.existsByName("ROLE_ADMIN_ROOT")) {
            RoleEntity adminRole = new RoleEntity();
            adminRole.setName("ROLE_ADMIN_ROOT");
            roleRepository.save(adminRole);

            Set<RoleEntity> roles = new HashSet<>();
            roles.add(adminRole);

            UserEntity admin = new UserEntity(
                    0,
                    "admin",
                    passwordEncoder.encode("admin"),
                    roles);
            userRepository.save(admin);
        }
    }

    @PostConstruct
    public void createRoleUser() {

        if (!roleRepository.existsByName("ROLE_USER")) {
            RoleEntity userRole = new RoleEntity();
            userRole.setName("ROLE_USER");
            roleRepository.save(userRole);
        }
    }

    @PostConstruct
    public void createRoleAdmin() {

        if (!roleRepository.existsByName("ROLE_ADMIN")) {
            RoleEntity adminRole = new RoleEntity();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);
        }
    }

    /**
     * Defines a clear role hierarchy with ROLE_ADMIN_ROOT as the top role, able to access everything.
     */
    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN_ROOT > ROLE_ADMIN > ROLE_USER");
        return roleHierarchy;
    }
}
