package dev.arol.petclinic.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.arol.petclinic.application.port.in.CreatePetUseCase;
import dev.arol.petclinic.application.port.in.GetPetUseCase;
import dev.arol.petclinic.application.port.in.GetPetsUseCase;
import dev.arol.petclinic.application.port.in.PetExistsUseCase;
import dev.arol.petclinic.application.port.out.PetRepository;
import dev.arol.petclinic.domain.model.Pet;

@Service
public class PetUseCaseImpl implements CreatePetUseCase, GetPetsUseCase, PetExistsUseCase, GetPetUseCase {

	private final PetRepository petRepository;

	public PetUseCaseImpl(PetRepository petRepository) {
		this.petRepository = petRepository;
	}

	@Override
	public Pet createPet(Pet pet) {
		pet.validateForCreation();
		return petRepository.save(pet);
	}

	@Override
	public List<Pet> getAllPets() {
		return petRepository.findAll();
	}

	@Override
	public boolean petExists(Long petId) {
		return petRepository.existsById(petId);
	}

	@Override
	public Pet getPetById(Long idPet) {
		if (!petRepository.existsById(idPet)) {
			throw new IllegalArgumentException("Pet with ID " + idPet + " does not exist");
		}
		return petRepository.findById(idPet).get();
	}
}