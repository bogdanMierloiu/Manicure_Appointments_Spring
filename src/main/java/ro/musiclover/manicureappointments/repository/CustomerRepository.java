package ro.musiclover.manicureappointments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.musiclover.manicureappointments.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
