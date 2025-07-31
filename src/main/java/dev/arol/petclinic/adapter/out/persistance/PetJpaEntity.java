package dev.arol.petclinic.adapter.out.persistance;

import dev.arol.petclinic.domain.model.Pet;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pets")
public class PetJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String species;
    
    @Column(name = "owner_name", nullable = false)
    private String ownerName;

    public PetJpaEntity() {
    	
    }
    
	public PetJpaEntity(Long id, String name, String species, String ownerName) {
		super();
		this.id = id;
		this.name = name;
		this.species = species;
		this.ownerName = ownerName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}


	// Método para convertir a entidad de dominio
    public Pet toDomain() {
        return new Pet(id, name, species, ownerName);
    }
    
    // Método para crear desde entidad de dominio
    public static PetJpaEntity fromDomain(Pet pet) {
        return new PetJpaEntity(pet.getId(), pet.getName(), 
                               pet.getSpecies(), pet.getOwnerName());
    }
}
