package ro.musiclover.manicureappointments.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ro.musiclover.manicureappointments.entity.Customer;
import ro.musiclover.manicureappointments.entity.NailsCare;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor

public class MyRepository {
    private final EntityManager entityManager;

    public List<Customer> findByFirstName(String name) {
        return entityManager.createQuery(
                "select t from Customer t where t.firstName=:name", Customer.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<NailsCare> findByServiceName(String name) {
        return entityManager.createQuery("select t from NailsCare t where t.serviceName=:name", NailsCare.class)
                .setParameter("name", name)
                .getResultList();
    }
}
