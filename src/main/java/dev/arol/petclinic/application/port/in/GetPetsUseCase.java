package dev.arol.petclinic.application.port.in;

import java.util.List;

import dev.arol.petclinic.domain.model.Pet;

public interface GetPetsUseCase {
    List<Pet> getAllPets();
}
