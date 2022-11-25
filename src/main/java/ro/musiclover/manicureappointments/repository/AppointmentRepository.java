package ro.musiclover.manicureappointments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.musiclover.manicureappointments.entity.Appointment;
import ro.musiclover.manicureappointments.model.appointment.AppointmentResponse;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    @Query("select t from Appointment t where t.appointmentDate = :date")
    List<AppointmentResponse> findByAppointmentDate(@Param("date") LocalDate date);
}
