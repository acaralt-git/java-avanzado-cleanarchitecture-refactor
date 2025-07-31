package dev.arol.petclinic.adapter.out.persistance;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import dev.arol.petclinic.application.port.out.PetRepository;
import dev.arol.petclinic.domain.model.Pet;

@Repository
@Profile({"h2", "postgres"})
public class PetRepositoryAdapter implements PetRepository {
    
    private final PetRepositoryJpa petRepositoryJpa;
    
    public PetRepositoryAdapter(PetRepositoryJpa petRepositoryJpa) {
        this.petRepositoryJpa = petRepositoryJpa;
    }
    
    @Override
    public Pet save(Pet pet) {
        PetJpaEntity entity = PetJpaEntity.fromDomain(pet);
        PetJpaEntity saved = petRepositoryJpa.save(entity);
        return saved.toDomain();
    }
    
    @Override
    public List<Pet> findAll() {
        return petRepositoryJpa.findAll()
                .stream()
                .map(PetJpaEntity::toDomain)
                .toList();
    }

	@Override
	public Optional<Pet> findById(Long id) {
		if(id == null) {
			return Optional.empty();
		}
		Optional<PetJpaEntity> pet = petRepositoryJpa.findById(id);
		return pet.map(PetJpaEntity::toDomain);
	}

	@Override
	public boolean existsById(Long id) {
		if(id == null) {
			return false;
		}
		Optional<PetJpaEntity> pet = petRepositoryJpa.findById(id);
		return pet.isPresent();
	}
    
}