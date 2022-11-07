package ro.musiclover.manicureappointments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.musiclover.manicureappointments.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
}
