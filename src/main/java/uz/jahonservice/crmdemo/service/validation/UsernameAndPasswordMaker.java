package uz.jahonservice.crmdemo.service.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.jahonservice.crmdemo.repository.UserRepository;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class UsernameAndPasswordMaker {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Random random;

    public String UsernameMaker() {
        String username = "1BK";
        while (true) {
            username = username.concat(String.valueOf(random.nextInt(10000, 100000)));
            if (!userRepository.existsByUserName(username)) break;
            username = username.substring(3);
        }
        return username;
    }

    public String PasswordMaker() {
        return String.valueOf(random.nextInt(100000, 999999));
    }


}
