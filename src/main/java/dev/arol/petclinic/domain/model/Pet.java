package dev.arol.petclinic.domain.model;

public class Pet {

	private Long id;
	private String name;
	private String species;
	private String ownerName;

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

	public Pet() {
	}

	public Pet(Long id, String name, String species, String ownerName) {
		this.id = id;
		this.name = name;
		this.species = species;
		this.ownerName = ownerName;
	}

	public boolean isValidForAppointment() {
		return name != null && !name.trim().isEmpty() && species != null && !species.trim().isEmpty();
	}

	public void validateForCreation() {
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("El nombre de la mascota es obligatorio");
		}
		if (species == null || species.trim().isEmpty()) {
			throw new IllegalArgumentException("La especie de la mascota es obligatoria");
		}
	}
}