package dev.arol.petclinic.domain.model;

import java.time.LocalDateTime;

public class Appointment {

	public Appointment() {
	}

	public Appointment(Long id, Long petId, LocalDateTime date, String reason) {
		this.id = id;
		this.petId = petId;
		this.date = date;
		this.reason = reason;
	}

	private Long id;
	private Long petId;
	private LocalDateTime date;
	private String reason;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPetId() {
		return petId;
	}

	public void setPetId(Long petId) {
		this.petId = petId;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public boolean isValidForAppointment() {
		return petId != null && date != null;
	}

	public void validateForCreation() {
		if (petId == null) {
			throw new IllegalArgumentException("PetId obligatoria");
		}
		if (reason == null) {
			throw new IllegalArgumentException("Reason obligatoria");
		}
		if (date == null) {
			throw new IllegalArgumentException("Data obligatoria");
		}
	}
}