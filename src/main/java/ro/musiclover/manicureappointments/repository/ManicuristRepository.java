package ro.musiclover.manicureappointments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.musiclover.manicureappointments.entity.Manicurist;

public interface ManicuristRepository extends JpaRepository<Manicurist, Integer> {
}
