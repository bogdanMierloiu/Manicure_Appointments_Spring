package ro.musiclover.manicureappointments.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.musiclover.manicureappointments.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

//    @Query("select t from Customer t where t.firstName = :firstName")
    List<Customer> findByFirstName(@Param("firstName") String firstName);

//    List<Customer> findByFirstName(String name);
}
