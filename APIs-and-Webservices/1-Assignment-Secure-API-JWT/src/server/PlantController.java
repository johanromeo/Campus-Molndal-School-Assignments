package com.jrome.plant.controllers;

import com.jrome.plant.entities.Plant;
import com.jrome.plant.services.PlantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling plant-related http-requests.
 */
@RestController
@RequestMapping("api/v1/plants")
@RequiredArgsConstructor
public class PlantController {

    private final PlantService plantService;

    @GetMapping("/{id}")
    public ResponseEntity<Plant> findPlantById(@PathVariable Long id) {

        return new ResponseEntity<>(plantService.findPlantById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Plant>> findAllPlants() {

        return new ResponseEntity<>(plantService.findAllPlants(), HttpStatus.OK);
    }

    // @Valid ensures that the requirements inside the Plant.class, aka @NotBlank, are met
    @PostMapping("/")
    public ResponseEntity<Plant> savePlant(@Valid @RequestBody Plant plant) {

        Plant createdPlant = plantService.savePlant(plant);

        return new ResponseEntity<>(createdPlant, HttpStatus.CREATED);
    }

    // Only ROLE_GARDEN_MASTER has authorization for PUT-request
    @PreAuthorize("hasRole('GARDEN_MASTER')")
    @PutMapping("/{id}")
    public ResponseEntity<Plant> updatePlant(@RequestBody Plant plant, @PathVariable Long id) {

        Plant updatedPlant = plantService.updatePlant(plant, id);

        return new ResponseEntity<>(updatedPlant, HttpStatus.OK);
    }

    // Only ROLE_GARDEN_MASTER has authorization for DELETE-request
    @PreAuthorize("hasRole('GARDEN_MASTER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlant(@PathVariable Long id) {

        plantService.deletePlantById(id);

        return ResponseEntity.ok(String.format("Plant with id %d is deleted", id));
    }
}
