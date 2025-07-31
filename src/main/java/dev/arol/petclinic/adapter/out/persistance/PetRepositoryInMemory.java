package dev.arol.petclinic.adapter.out.persistance;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import dev.arol.petclinic.application.port.out.PetRepository;
import dev.arol.petclinic.domain.model.Pet;

@Repository
@Profile("inmemory")
public class PetRepositoryInMemory implements PetRepository {
    
    private final ConcurrentHashMap<Long, Pet> pets = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    
    @Override
    public Pet save(Pet pet) {
        if (pet.getId() == null) {
            pet = new Pet(idGenerator.getAndIncrement(), 
                         pet.getName(), pet.getSpecies(), pet.getOwnerName());
        }
        pets.put(pet.getId(), pet);
        return pet;
    }

	@Override
	public List<Pet> findAll() {
		return new ArrayList<>(pets.values());
	}

	@Override
	public Optional<Pet> findById(Long id) {
		if(id == null) {
			return Optional.empty();
		}
		return Optional.ofNullable(pets.get(id));
	}

	@Override
	public boolean existsById(Long id) {
		return pets.get(id) != null;
	}
    
}