package ro.musiclover.manicureappointments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.musiclover.manicureappointments.entity.NailsService;

public interface NailsServiceRepository extends JpaRepository<NailsService, Integer> {
}
