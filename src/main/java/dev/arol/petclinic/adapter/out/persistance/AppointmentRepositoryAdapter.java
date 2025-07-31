package dev.arol.petclinic.adapter.out.persistance;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import dev.arol.petclinic.application.port.out.AppointmentRepository;
import dev.arol.petclinic.domain.model.Appointment;

@Repository
@Profile({"h2", "postgres"})
public class AppointmentRepositoryAdapter implements AppointmentRepository {
    
    private final AppointmentRepositoryJpa appointmentRepositoryJpa;
    
    public AppointmentRepositoryAdapter(AppointmentRepositoryJpa appointmentRepositoryJpa) {
        this.appointmentRepositoryJpa = appointmentRepositoryJpa;
    }
    
    @Override
    public Appointment save(Appointment appointment) {
        AppointmentJpaEntity entity = AppointmentJpaEntity.fromDomain(appointment);
        AppointmentJpaEntity saved = appointmentRepositoryJpa.save(entity);
        return saved.toDomain();
    }
    
    @Override
    public List<Appointment> findAll() {
        return appointmentRepositoryJpa.findAll()
                .stream()
                .map(AppointmentJpaEntity::toDomain)
                .toList();
    }

	@Override
	public Optional<Appointment> findById(Long id) {
		if(id == null) {
			return Optional.empty();
		}
		Optional<AppointmentJpaEntity> appointment = appointmentRepositoryJpa.findById(id);
		return appointment.map(AppointmentJpaEntity::toDomain);
	}

	@Override
	public boolean existsById(Long id) {
		if(id == null) {
			return false;
		}
		Optional<AppointmentJpaEntity> appointment = appointmentRepositoryJpa.findById(id);
		return appointment.isPresent();
	}
    
}