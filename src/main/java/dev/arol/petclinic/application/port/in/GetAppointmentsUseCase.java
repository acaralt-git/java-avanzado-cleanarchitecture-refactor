package dev.arol.petclinic.application.port.in;

import java.util.List;

import dev.arol.petclinic.domain.model.Appointment;

public interface GetAppointmentsUseCase {
    List<Appointment> getAllAppointments();
}
