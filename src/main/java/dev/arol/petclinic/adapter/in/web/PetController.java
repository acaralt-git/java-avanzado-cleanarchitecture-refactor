package dev.arol.petclinic.adapter.in.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.arol.petclinic.application.port.in.CreatePetUseCase;
import dev.arol.petclinic.application.port.in.GetPetUseCase;
import dev.arol.petclinic.application.port.in.GetPetsUseCase;
import dev.arol.petclinic.domain.model.Pet;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pets")
public class PetController {

	private final CreatePetUseCase createPetUseCase;
	private final GetPetsUseCase getPetsUseCase;
	private final GetPetUseCase getPetUseCase;

	public PetController(CreatePetUseCase createPetUseCase, GetPetsUseCase getPetsUseCase,
			GetPetUseCase getPetUseCase) {
		this.createPetUseCase = createPetUseCase;
		this.getPetsUseCase = getPetsUseCase;
		this.getPetUseCase = getPetUseCase;
	}

	@PostMapping
	public ResponseEntity<Pet> createPet(@Valid @RequestBody Pet pet) {
		Pet createdPet = createPetUseCase.createPet(pet);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdPet);
	}

	@GetMapping
	public ResponseEntity<List<Pet>> getAllPets() {
		List<Pet> pets = getPetsUseCase.getAllPets();
		return ResponseEntity.ok(pets);
	}

	@GetMapping("/{idPet}")
	public ResponseEntity<?> getPet(@PathVariable Long idPet) {
		Pet pet = null;
		try {
			pet = getPetUseCase.getPetById(idPet);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(pet);
	}
}