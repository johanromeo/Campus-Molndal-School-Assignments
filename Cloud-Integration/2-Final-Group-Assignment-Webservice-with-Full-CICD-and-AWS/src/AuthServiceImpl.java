package com.jromeo.webservice.auth.service;

import com.jromeo.webservice.auth.dto.LoginDTO;
import com.jromeo.webservice.auth.dto.RegisterDTO;
import com.jromeo.webservice.auth.entity.RoleEntity;
import com.jromeo.webservice.auth.entity.UserEntity;
import com.jromeo.webservice.auth.repository.RoleRepository;
import com.jromeo.webservice.auth.repository.UserRepository;
import com.jromeo.webservice.auth.security.JWTTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Author: Johan Romeo
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

    private boolean isUsernameExisting(RegisterDTO dto) {

        if (userRepository.existsByUsername(dto.getUsername())) {
            System.out.println("Username already exists!");
        }

        return false;
    }


    private Set<RoleEntity> setUserRole() {

        Set<RoleEntity> roles = new HashSet<>();
        RoleEntity userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);

        return roles;
    }

    private UserEntity createNewUser(RegisterDTO dto) {


        var user = new UserEntity();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));


        return user;
    }

    @Override
    public String register(RegisterDTO dto) {

        if (!isUsernameExisting(dto)) {

            var customer = createNewUser(dto);

            Set<RoleEntity> userRole = setUserRole();
            customer.setRoles(userRole);

            userRepository.save(customer);
        }
        return "User registered successfully!";
    }

    @Override
    public String login(LoginDTO dto) {

        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dto.getUsername(),
                dto.getPassword()));

        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);

        return jwtTokenProvider.generateJwtToken(authentication);
    }
}
