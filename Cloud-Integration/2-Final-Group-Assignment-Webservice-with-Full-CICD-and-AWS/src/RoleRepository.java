package com.jromeo.webservice.auth.repository;

import com.jromeo.webservice.auth.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Author: Johan Romeo
 */

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    Optional<RoleEntity> findByName(String roleName);

    Boolean existsByName(String roleName);
}
