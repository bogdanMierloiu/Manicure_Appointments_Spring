package ro.musiclover.manicureappointments.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import ro.musiclover.manicureappointments.entity.User;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    private UserRepository userRepository;
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setPassword("test123");
        user.setFirstName("TestFName");
        user.setLastName("TestLName");

        User savedUser = userRepository.save(user);
        User existUser = entityManager.find(User.class, savedUser.getId());
        assertThat(existUser.getEmail().equals(user.getEmail()));

    }

    @Test
    public void testFindUserByEmail() {
        String email = "bogdy.musiclover@gmail.com";

        User user = userRepository.findByEmail(email);
        assertThat(user).isNotNull();
    }
}
