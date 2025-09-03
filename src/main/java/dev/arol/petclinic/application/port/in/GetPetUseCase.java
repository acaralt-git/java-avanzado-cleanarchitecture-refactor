package dev.arol.petclinic.application.port.in;

import dev.arol.petclinic.domain.model.Pet;

public interface GetPetUseCase {
	Pet getPetById(Long idPet);
}
