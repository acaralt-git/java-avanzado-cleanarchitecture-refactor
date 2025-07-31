package dev.arol.petclinic.adapter.in.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.arol.petclinic.application.port.in.CreateAppointmentUseCase;
import dev.arol.petclinic.application.port.in.GetAppointmentsUseCase;
import dev.arol.petclinic.domain.model.Appointment;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

	private final CreateAppointmentUseCase createAppointmentUseCase;
	private final GetAppointmentsUseCase getAppointmentsUseCase;

	public AppointmentController(CreateAppointmentUseCase createAppointmentUseCase,
			GetAppointmentsUseCase getAppointmentsUseCase) {
		this.createAppointmentUseCase = createAppointmentUseCase;
		this.getAppointmentsUseCase = getAppointmentsUseCase;
	}

	@PostMapping
	public ResponseEntity<?> createAppointment(@Valid @RequestBody Appointment appointment) {
		Appointment createdAppointment = null;
		try {
			createdAppointment = createAppointmentUseCase.createAppointment(appointment);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(createdAppointment);
	}

	@GetMapping
	public ResponseEntity<List<Appointment>> getAllAppointments() {
		List<Appointment> appointments = getAppointmentsUseCase.getAllAppointments();
		return ResponseEntity.ok(appointments);
	}
}