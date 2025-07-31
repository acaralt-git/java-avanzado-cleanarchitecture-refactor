package dev.arol.petclinic.application.port.out;

import java.util.List;
import java.util.Optional;

import dev.arol.petclinic.domain.model.Pet;

public interface PetRepository {
    Pet save(Pet pet);
    List<Pet> findAll();
    Optional<Pet> findById(Long id);
    boolean existsById(Long id);
}