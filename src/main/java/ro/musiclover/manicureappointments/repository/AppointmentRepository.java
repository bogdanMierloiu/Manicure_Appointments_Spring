package ro.musiclover.manicureappointments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.musiclover.manicureappointments.entity.Appointment;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    List<Appointment> findAllByOrderByAppointmentDateTimeDesc();

    List<Appointment> findByAppointmentDateTime(LocalDateTime date);

    @Query("select t from Appointment t where t.appointmentDateTime BETWEEN :startDate AND :endDate")
    List<Appointment> findByAppointmentDateBetween(
            @Param("startDate") LocalDateTime dateFrom,
            @Param("endDate") LocalDateTime dateTo);
}
