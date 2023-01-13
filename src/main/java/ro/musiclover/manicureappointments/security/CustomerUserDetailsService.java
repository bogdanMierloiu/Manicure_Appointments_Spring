package ro.musiclover.manicureappointments.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.musiclover.manicureappointments.entity.User;
import ro.musiclover.manicureappointments.repository.UserRepository;
@Service
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    public CustomerUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
//    @Autowired
//    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }
}
