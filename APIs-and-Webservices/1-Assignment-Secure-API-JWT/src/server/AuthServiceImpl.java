package com.jrome.auth.services.impl;

import com.jrome.auth.entities.Role;
import com.jrome.auth.entities.User;
import com.jrome.auth.payloads.LoginDTO;
import com.jrome.auth.payloads.RegisterDTO;
import com.jrome.auth.repositories.RoleRepository;
import com.jrome.auth.repositories.UserRepository;
import com.jrome.auth.security.JWTTokenProvider;
import com.jrome.auth.services.AuthService;
import com.jrome.exceptions.EmailAlreadyExistException;
import com.jrome.exceptions.UsernameAlreadyExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashSet;
import java.util.Set;

/**
 * Service implementation for handling authentication-related operations.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTTokenProvider jwtTokenProvider;

    /**
     * Registers a new user with the provided information.
     *
     * @param dto The RegisterDTO containing user registration details.
     * @return A success message upon successful registration.
     * @throws HttpClientErrorException If the username or email already exists.
     */
    @Override
    public String register(RegisterDTO dto) {

        if (!isUserOrEmailExisting(dto)) {

            var gardenMaster = createNewUser(dto);

            Set<Role> gardenMasterRole = setGardenMasterRole();
            gardenMaster.setRoles(gardenMasterRole);

            userRepository.save(gardenMaster);
        }

        return "Garden Master registered successfully!";
    }

    private boolean isUserOrEmailExisting(RegisterDTO dto) {

        if (userRepository.existsByUsername(dto.getUsername()))
            throw new UsernameAlreadyExistException(String.format("Username: %s already exist!", dto.getUsername()));

        if (userRepository.existsByEmail(dto.getEmail()))
            throw new EmailAlreadyExistException(String.format("Email: %s already exist!", dto.getEmail()));

        return false;
    }

    public Set<Role> setGardenMasterRole() {

        Set<Role> roles = new HashSet<>();
        // The ROLE_GARDEN_MASTER is automatically set to a newly registered user by default
        Role gardenMasterRole = roleRepository.findByName("ROLE_GARDEN_MASTER").get();
        roles.add(gardenMasterRole);

        return roles;
    }

    private User createNewUser(RegisterDTO dto) {

        var gardenMaster = new User();
        gardenMaster.setName(dto.getName());
        gardenMaster.setUsername(dto.getUsername());
        gardenMaster.setEmail(dto.getEmail());
        gardenMaster.setPassword(passwordEncoder.encode(dto.getPassword()));

        return gardenMaster;
    }

    /**
     * Performs user login and generates a JWT token upon successful authentication.
     *
     * @param dto The LoginDTO containing user credentials.
     * @return The generated JWT token.
     */
    @Override
    public String login(LoginDTO dto) {

        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dto.getUsernameOrEmail(), dto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenProvider.generateJwtToken(authentication);
    }
}
