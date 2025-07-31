package dev.arol.petclinic.application.port.out;

import java.util.List;
import java.util.Optional;

import dev.arol.petclinic.domain.model.Appointment;

public interface AppointmentRepository {
	Appointment save(Appointment pet);
    List<Appointment> findAll();
    Optional<Appointment> findById(Long id);
    boolean existsById(Long id);
}