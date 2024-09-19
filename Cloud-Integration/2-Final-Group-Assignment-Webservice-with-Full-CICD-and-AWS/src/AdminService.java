package com.jromeo.webservice.auth.service;

import com.jromeo.webservice.auth.entity.RoleEntity;
import com.jromeo.webservice.auth.entity.UserEntity;
import com.jromeo.webservice.auth.repository.RoleRepository;
import com.jromeo.webservice.auth.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Author: Johan Romeo and Sandra Jeppsson Kristiansson
 */
@Service
@AllArgsConstructor
public class AdminService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserEntity findUserById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with id: " + userId + " doesn't exist"));
    }

    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity updateUserRole(Integer userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with id: " + userId + "doesn't exist"));

        Optional<RoleEntity> role = roleRepository.findByName("ROLE_ADMIN");
        Set<RoleEntity> userRole = user.getRoles();
        userRole.add(role.get());

        return userRepository.save(user);
    }

    public UserEntity removeRole(Integer userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with id: " + userId + " doesn't exist"));
        Set<RoleEntity> userRoles = user.getRoles();

        userRoles.removeIf(userRole -> userRole.getName().equals("ROLE_ADMIN"));
        return userRepository.save(user);
    }

    public void deleteUser(Integer userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with id: " + userId + " doesn't exist"));
        Set<RoleEntity> userRoles = user.getRoles();

        for (RoleEntity userRole : userRoles) {
            if (userRole.getName().equals("ROLE_ADMIN_ROOT")) {
                throw new RuntimeException("User with id: " + userId + " is the root admin and cannot be deleted");
            }
        }
        userRepository.delete(user);
    }
}
