package dev.arol.petclinic.adapter.in.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.arol.petclinic.application.port.in.CreatePetUseCase;
import dev.arol.petclinic.application.port.in.GetPetsUseCase;
import dev.arol.petclinic.domain.model.Pet;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pets")
public class PetController {
    
    private final CreatePetUseCase createPetUseCase;
    private final GetPetsUseCase getPetsUseCase;
    
    public PetController(CreatePetUseCase createPetUseCase, 
                        GetPetsUseCase getPetsUseCase) {
        this.createPetUseCase = createPetUseCase;
        this.getPetsUseCase = getPetsUseCase;
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
}