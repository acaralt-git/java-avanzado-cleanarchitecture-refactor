package dev.arol.petclinic.application.port.in;

public interface AppointmentExistsUseCase {
    boolean appointmentExists(Long petId);
}