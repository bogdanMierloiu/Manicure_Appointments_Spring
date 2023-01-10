package ro.musiclover.manicureappointments.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ro.musiclover.manicureappointments.entity.User;
import ro.musiclover.manicureappointments.repository.UserRepository;


class UserServiceTest {

    @Test
    void createUser() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        UserService userService = new UserService(userRepository);
        User user = new User();
        user.setFirstName("Bogdan");
        user.setLastName("Mierloiu");
        user.setEmail("bogdan.mierloiu01@gmail.com");
        user.setPassword("abcd");
        userService.createUser(user);
        System.out.println(userService.getAll());

    }
}