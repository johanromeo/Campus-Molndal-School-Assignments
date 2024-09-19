package com.jrome.plant.repositories;

import com.jrome.plant.entities.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for accessing and managing plant entities in the database.
 */
@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {

}
