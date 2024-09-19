package com.jrome.plant.services;

import com.jrome.exceptions.NoSuchPlantException;
import com.jrome.plant.entities.Plant;
import com.jrome.plant.repositories.PlantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for handling plant-related business logic.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PlantService {

    private final PlantRepository plantRepository;

    public Plant findPlantById(Long id) {

        return plantRepository.findById(id)
                .orElseThrow(() -> new NoSuchPlantException(String.format("Plant with id %d does not exist", id)));

    }

    public List<Plant> findAllPlants() {

        List<Plant> plants = plantRepository.findAll();

        if (plants.isEmpty()) {
            throw new NoSuchPlantException("It looks really empty in here... Plant a seed and try again");
        }

        return plants;
    }

    public Plant savePlant(Plant plant) {

        return plantRepository.save(plant);
    }

    public Plant updatePlant(Plant plant, Long id) {

        if (!plantRepository.existsById(id))
            throw new NoSuchPlantException(String.format("Can't update plant with id: %d because there is none", id));

        else {
            plant.setId(id);
            return plantRepository.save(plant);
        }
    }

    public void deletePlantById(Long id) {

        if (!plantRepository.existsById(id))
            throw new NoSuchPlantException(String.format("Can't delete plant with id: %d because there is none", id));

        plantRepository.deleteById(id);
    }
}
